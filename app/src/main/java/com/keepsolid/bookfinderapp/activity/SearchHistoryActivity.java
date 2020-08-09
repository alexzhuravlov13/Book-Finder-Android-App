package com.keepsolid.bookfinderapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.adapter.SearchHistoryRecyclerAdapter;
import com.keepsolid.bookfinderapp.base.BaseActivity;
import com.keepsolid.bookfinderapp.utils.ApplicationSettingsManager;
import com.keepsolid.bookfinderapp.utils.Constants;
import com.keepsolid.bookfinderapp.utils.listeners.OnHistoryRecyclerItemClickListener;

import java.util.LinkedList;
import java.util.List;

public class SearchHistoryActivity extends BaseActivity {
    private List<String> historyItems;
    private RecyclerView recycler;
    private SearchHistoryRecyclerAdapter adapter;
    private FloatingActionButton clearButton;
    private ApplicationSettingsManager applicationSettingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        initToolbar("Search history");

        historyItems = new LinkedList<>();

        applicationSettingsManager = new ApplicationSettingsManager(this);

        checkCachedItems();

        initViews();

        initListeners();

        initRvAndAdapter();

        enableUpButton();

        initSnackBar();

    }

    private void initListeners() {
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyItems.clear();
                applicationSettingsManager.cacheLoadedItems(historyItems);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initViews() {
        recycler = findViewById(R.id.rv_recycler);

        clearButton = findViewById(R.id.btn_clear);
    }

    private void initRvAndAdapter() {
        adapter = new SearchHistoryRecyclerAdapter(historyItems, this, new OnHistoryRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String string) {
                searchBook(string);
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    private void initSnackBar() {
        String snackBarText;
        if (!historyItems.isEmpty()) {
            snackBarText = "Choose string to repeat search";
        } else {
            snackBarText = "Your search history will appear here";
        }
        showSnackBar(snackBarText);
    }

    private void searchBook(String string) {
        Intent viewIntent = new Intent(SearchHistoryActivity.this, MainActivity.class);
        viewIntent.putExtra(Constants.KEY_RES_ID, string);
        startActivity(viewIntent);
    }

    private void checkCachedItems() {
        List<String> cachedItems = applicationSettingsManager.getCachedItems();
        if (cachedItems != null && !cachedItems.isEmpty()) {
            historyItems.addAll(cachedItems);
        }
    }
}