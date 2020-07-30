package com.keepsolid.gittestapp.fragment;

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
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.adapter.BookRecyclerAdapter;
import com.keepsolid.gittestapp.api.ApiCallback;
import com.keepsolid.gittestapp.api.RestClient;
import com.keepsolid.gittestapp.model.BookErrorItem;
import com.keepsolid.gittestapp.model.BookItem;
import com.keepsolid.gittestapp.model.GoogleBooksResponse;
import com.keepsolid.gittestapp.utils.KeyboardUtils;
import com.keepsolid.gittestapp.utils.listeners.OnBookRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;


public class ChooserFragment extends Fragment {

    private RecyclerView recycler;
    private View loaderBlock;

    private FloatingActionButton goButton;
    private ProgressBar progressBar;
    private TextInputEditText userInput;
    private ArrayList<BookItem> items;
    private BookRecyclerAdapter adapter;
    private OnBookRecyclerItemClickListener listener;

    public ChooserFragment() {
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
        goButton = view.findViewById(R.id.btn_find);

        items = new ArrayList<>();

        adapter = new BookRecyclerAdapter(items, getContext());

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        initListeners();

        return view;
    }

    private void handleSearchAction() {
        if (TextUtils.isEmpty(userInput.getText().toString())) {
            userInput.requestFocus();
        } else {
            KeyboardUtils.hide(userInput);
            loadBooks(userInput.getText().toString());
        }

    }

    private void loadBooks(String title) {
        showProgressBlock();
        RestClient.getInstance().getApiService().getBooks(title).enqueue(new ApiCallback<GoogleBooksResponse>() {
            @Override
            public void success(Response<GoogleBooksResponse> response) {
                items.clear();
                if (response.body().getTotalItems()==0) {
                    makeErrorToast("No Such Items");
                } else {
                    items.addAll(response.body().getBookItems());
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

        goButton.setOnClickListener(new View.OnClickListener() {
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
}