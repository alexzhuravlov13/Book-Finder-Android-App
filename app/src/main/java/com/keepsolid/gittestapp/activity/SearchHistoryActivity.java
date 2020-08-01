package com.keepsolid.gittestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.adapter.SearchHistoryRecyclerAdapter;
import com.keepsolid.gittestapp.utils.ApplicationSettingsManager;
import com.keepsolid.gittestapp.utils.Constants;
import com.keepsolid.gittestapp.utils.listeners.OnHistoryRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchHistoryActivity extends BaseActivity {
    private List<String> historyItems;
    private RecyclerView recycler;
    private SearchHistoryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        initToolbar("Search history");

        historyItems = new LinkedList<>();
        checkCachedItems();

        recycler = findViewById(R.id.rv_recycler);
        adapter = new SearchHistoryRecyclerAdapter(historyItems, this, new OnHistoryRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String string) {
                searchBook(string);
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        initHistoryMenu();

        showSnackBar("Choose string to repeat search");

    }

    private void searchBook(String string) {
        Intent viewIntent = new Intent(SearchHistoryActivity.this, MainActivity.class);
        viewIntent.putExtra(Constants.KEY_RES_ID, string);
        startActivity(viewIntent);
    }

    private void checkCachedItems() {
        List<String> cachedItems = ApplicationSettingsManager.getCachedItems(getApplicationContext());
        if (cachedItems != null && !cachedItems.isEmpty()) {
            historyItems.addAll(cachedItems);
        }
    }

    public void initHistoryMenu() {
        MaterialToolbar toolbar = getToolbar();
        toolbar.inflateMenu(R.menu.history_menu);
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_clear_history) {
                    ApplicationSettingsManager.cacheLoadedItems(getApplicationContext(), new ArrayList<>());
                    adapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
    }
}