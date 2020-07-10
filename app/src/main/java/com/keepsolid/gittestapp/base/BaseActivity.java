package com.keepsolid.gittestapp.base;

import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.keepsolid.gittestapp.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary_light);
        toolbar.setTitleTextColor(color);


    }
}