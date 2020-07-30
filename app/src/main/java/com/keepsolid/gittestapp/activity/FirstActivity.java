package com.keepsolid.gittestapp.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.adapter.BookRecyclerAdapter;
import com.keepsolid.gittestapp.api.ApiCallback;
import com.keepsolid.gittestapp.api.RestClient;
import com.keepsolid.gittestapp.model.BookErrorItem;
import com.keepsolid.gittestapp.model.BookItem;
import com.keepsolid.gittestapp.model.GoogleBooksResponse;
import com.keepsolid.gittestapp.utils.listeners.OnBookRecyclerItemClickListener;

import java.util.ArrayList;

import retrofit2.Response;


public class FirstActivity extends BaseActivity {
    private RecyclerView recycler;
    private View loaderBlock;

    private AppCompatButton goButton;
    private ProgressBar progressBar;
    private AppCompatEditText userInput;
    private ArrayList<BookItem> items;
    private BookRecyclerAdapter adapter;

    private OnBookRecyclerItemClickListener listener;

    private boolean isInLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initToolbar(getString(R.string.app_name));

        loaderBlock = findViewById(R.id.loader_block);
        recycler = findViewById(R.id.rv_recycler);
        userInput = findViewById(R.id.et_user_input);
        goButton = findViewById(R.id.btn_go);

        items = new ArrayList<>();

        adapter = new BookRecyclerAdapter(items, this, listener);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        initListeners();

    }

    private void handleSearchAction() {
        //TODO: focus on text if empty, hide keyboard if not and find
        loadBooks(userInput.getText().toString());
    }

    private void loadBooks(String title) {
        showProgressBlock();
        RestClient.getInstance().getApiService().getBooks(title).enqueue(new ApiCallback<GoogleBooksResponse>() {
            @Override
            public void success(Response<GoogleBooksResponse> response) {
                    items.clear();
                    items.addAll(response.body().getBookItems());
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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
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

    private void initViews() {

        isInLandscapeMode = findViewById(R.id.fragment_viewer) != null;

        if (isInLandscapeMode) {
            //viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
        }

        //TODO: 2nd fragment in landscape

    }

    private void initListeners() {
        listener = new OnBookRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                displaySelected(position);
            }
        };

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSearchAction();
            }
        });

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_GO){
                    handleSearchAction();
                    return true;
                }
                return false;
            }
        });

    }

    private void displaySelected(int selectedImageResId) {
    }
}