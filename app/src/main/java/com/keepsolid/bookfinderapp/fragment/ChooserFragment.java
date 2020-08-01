package com.keepsolid.bookfinderapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.activity.MainActivity;
import com.keepsolid.bookfinderapp.adapter.BookRecyclerAdapter;
import com.keepsolid.bookfinderapp.api.ApiCallback;
import com.keepsolid.bookfinderapp.api.RestClient;
import com.keepsolid.bookfinderapp.model.BookErrorItem;
import com.keepsolid.bookfinderapp.model.BookItem;
import com.keepsolid.bookfinderapp.model.GoogleBooksResponse;
import com.keepsolid.bookfinderapp.utils.ApplicationSettingsManager;
import com.keepsolid.bookfinderapp.utils.KeyboardUtils;
import com.keepsolid.bookfinderapp.utils.db.AppDatabase;
import com.keepsolid.bookfinderapp.utils.listeners.OnBookRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;


public class ChooserFragment extends Fragment {

    private RecyclerView recycler;
    private View loaderBlock;

    private FloatingActionButton fingButton;
    private ProgressBar progressBar;
    private TextInputEditText userInput;
    private ArrayList<BookItem> items;
    private BookRecyclerAdapter adapter;
    private OnBookRecyclerItemClickListener listener;

    public ChooserFragment() {
    }

    public ArrayList<BookItem> getItems() {
        return items;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        recycler = view.findViewById(R.id.rv_recycler);
        loaderBlock = view.findViewById(R.id.loader_block);
        progressBar = view.findViewById(R.id.pb_progress);
        userInput = view.findViewById(R.id.et_user_input);
        fingButton = view.findViewById(R.id.btn_find);

        items = new ArrayList<>();
        checkCachedItems();

        adapter = new BookRecyclerAdapter(items, getContext());

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        initListeners();

        return view;
    }

    private void handleSearchAction() {
        String userInputText = userInput.getText().toString();
        if (TextUtils.isEmpty(userInputText)) {
            userInput.requestFocus();
        } else {
            performSearch(userInputText);
        }

    }

    private void performSearch(String userInputText) {
        KeyboardUtils.hide(userInput);
        String searchText;
        if (userInputText != null) {
            searchText = userInputText.trim();
            if (!searchText.isEmpty()) {
                List<String> cachedItems = ApplicationSettingsManager.getCachedItems(getActivity().getApplicationContext());
                LinkedList<String> historyItems = new LinkedList<>();
                if (cachedItems != null) {
                    historyItems.addAll(cachedItems);
                }
                historyItems.addFirst(searchText);
                ApplicationSettingsManager.cacheLoadedItems(getActivity().getApplicationContext(), historyItems);

                loadBooks(searchText);
            } else makeErrorToast("Put any text");
        }


    }

    private void loadBooks(String title) {
        showProgressBlock();
        RestClient.getInstance().getApiService().getBooks(title).enqueue(new ApiCallback<GoogleBooksResponse>() {
            @Override
            public void success(Response<GoogleBooksResponse> response) {
                items.clear();
                if (response.body().getTotalItems() == 0) {
                    makeErrorToast("No Such Items");
                } else {
                    items.addAll(response.body().getBookItems());
                }
                MainActivity activity = (MainActivity) getActivity();
                AppDatabase database = null;
                if (activity != null) {
                    database = activity.getDatabase();
                }
                if (database != null) {
                    database.bookItemDao().deleteAll();
                    database.bookItemDao().insert(items);
                }
                adapter.notifyDataSetChanged();

                hideProgressBlock();
            }

            @Override
            public void failure(BookErrorItem bookErrorItem) {
                makeErrorToast(bookErrorItem.getMessage());
                hideProgressBlock();
            }
        });
    }

    private void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void showProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }


    private void initListeners() {

        fingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSearchAction();
            }
        });

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    handleSearchAction();
                    return true;
                }
                return false;
            }
        });

    }

    public void setBookSelectListener(OnBookRecyclerItemClickListener listener) {
        adapter.setListener(listener);
    }

    private void checkCachedItems() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            AppDatabase database = activity.getDatabase();
            if (database != null) {
                database.bookItemDao().getAll().observe(this, gitRepoItems -> {
                    items.clear();
                    items.addAll(gitRepoItems);
                    adapter.notifyDataSetChanged();
                });
            }
        }
    }

    public void setUserInputAndFind(String string) {
        userInput.setText(string);
        handleSearchAction();
    }
}