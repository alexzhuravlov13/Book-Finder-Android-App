package com.keepsolid.gittestapp.base;

import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.SecondActivity;
import com.keepsolid.gittestapp.utils.Constants;

public abstract class BaseActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;

    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);

        setTitle(title);

        initMenu();
    }

    private void setTitle(String title) {
        toolbar.setTitle(title);
        int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary_light);
        toolbar.setTitleTextColor(color);
    }

    private void initMenu() {
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.htc_select: {
                        Intent viewIntent = new Intent(BaseActivity.this, SecondActivity.class);
                        viewIntent.putExtra(Constants.KEY_RES_ID, 0);
                        startActivity(viewIntent);
                        return true;
                    }

                    case R.id.moto_select: {
                        Intent viewIntent = new Intent(BaseActivity.this, SecondActivity.class);
                        viewIntent.putExtra(Constants.KEY_RES_ID, 1);
                        startActivity(viewIntent);
                        return true;
                    }

                    case R.id.pixel_select: {
                        Intent viewIntent = new Intent(BaseActivity.this, SecondActivity.class);
                        viewIntent.putExtra(Constants.KEY_RES_ID, 2);
                        startActivity(viewIntent);
                        return true;
                    }

                    case R.id.galaxy_select: {
                        Intent viewIntent = new Intent(BaseActivity.this, SecondActivity.class);
                        viewIntent.putExtra(Constants.KEY_RES_ID, 3);
                        startActivity(viewIntent);
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }
}