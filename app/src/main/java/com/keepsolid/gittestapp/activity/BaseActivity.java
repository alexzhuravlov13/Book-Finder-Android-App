package com.keepsolid.gittestapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.gittestapp.R;

public abstract class BaseActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;

    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);

        setTitle(title);
    }

    private void setTitle(String title) {
        toolbar.setTitle(title);
        int color = ContextCompat.getColor(getApplicationContext(), android.R.color.white);
        toolbar.setTitleTextColor(color);
    }

    public void enableUpButton(){
        setSupportActionBar(getToolbar());
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public MaterialToolbar getToolbar() {
        return toolbar;
    }

}