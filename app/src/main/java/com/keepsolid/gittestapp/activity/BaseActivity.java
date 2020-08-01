package com.keepsolid.gittestapp.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.app.BookFinderApp;
import com.keepsolid.gittestapp.utils.db.AppDatabase;

public abstract class BaseActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;


    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);

        setTitle(title);
    }

    public void initMainMenu() {
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_open_history) {
                    Intent intent = new Intent(BaseActivity.this, SearchHistoryActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void setTitle(String title) {
        toolbar.setTitle(title);
        int color = ContextCompat.getColor(getApplicationContext(), android.R.color.white);
        toolbar.setTitleTextColor(color);
    }

    public void enableUpButton() {
        setSupportActionBar(getToolbar());
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    public MaterialToolbar getToolbar() {
        return toolbar;
    }

    public AppDatabase getDatabase() {
        return ((BookFinderApp) getApplication()).getAppDatabase();
    }

    public void showSnackBar(String text) {
        int duration = Snackbar.LENGTH_LONG;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        snackbar.setAction("Hide", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        snackbar.show();
    }
}