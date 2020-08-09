package com.keepsolid.bookfinderapp.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.activity.DetailActivity;
import com.keepsolid.bookfinderapp.activity.MainActivity;
import com.keepsolid.bookfinderapp.adapter.BookRecyclerAdapter;
import com.keepsolid.bookfinderapp.model.BookItem;
import com.keepsolid.bookfinderapp.utils.Constants;
import com.keepsolid.bookfinderapp.utils.KeyboardUtils;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements MainContract.View {

    private RecyclerView recycler;
    private View loaderBlock;

    private FloatingActionButton findButton;
    private ProgressBar progressBar;
    private TextInputEditText userInput;
    private ArrayList<BookItem> items;
    private BookRecyclerAdapter adapter;
    private MainContract.Presenter presenter;

    public MainFragment() {
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
        findButton = view.findViewById(R.id.btn_find);

        if (items == null) {
            items = new ArrayList<>();
        }
        adapter = new BookRecyclerAdapter(items);

        recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recycler.setAdapter(adapter);

        initListeners();
        FragmentActivity activity = getActivity();
        if (activity != null && activity.getIntent().getExtras() != null) {
            String title = getActivity().getIntent().getStringExtra(Constants.KEY_RES_ID);
            userInput.setText(title);
            presenter.takeView(this);
            handleSearchAction();
        }

        presenter.takeView(this);

        return view;
    }

    private void handleSearchAction() {
        KeyboardUtils.show(userInput);
        Editable editable = userInput.getText();
        String userInputText = "";
        if (editable != null) {
            userInputText = editable.toString();
        }
        if (TextUtils.isEmpty(userInputText)) {
            userInput.requestFocus();
        } else {
            presenter.performSearch(userInputText);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }


    @Override
    public void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard() {
        //TODO: make method
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void showInputError() {
        userInput.requestFocus();
    }

    @Override
    public void showRequestError(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void observeItems(LiveData<List<BookItem>> itemsLiveData) {

        itemsLiveData.observe(MainFragment.this, new Observer<List<BookItem>>() {
            @Override
            public void onChanged(List<BookItem> gitRepoItems) {
                items.clear();
                items.addAll(gitRepoItems);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void stopObserving(LiveData<List<BookItem>> liveRepoData) {
        liveRepoData.removeObservers(MainFragment.this);
    }


    private void initListeners() {

        findButton.setOnClickListener(new View.OnClickListener() {
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

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            if (activity.isInLandscapeMode()) {
                adapter.setListener((v, position, volumeItem) -> activity.displaySelected(volumeItem));
            } else {
                adapter.setListener((v, position, volumeItem) -> {
                    Intent viewIntent = new Intent(getActivity(), DetailActivity.class);
                    viewIntent.putExtra(Constants.KEY_RES_ID, volumeItem);
                    startActivity(viewIntent);
                });
            }
        }


    }


    public void setUserInputAndFind(String string) {
        userInput.setText(string);
        handleSearchAction();
    }

    public void setItems(ArrayList<BookItem> items) {
        this.items = items;
    }
}