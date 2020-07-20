package com.keepsolid.gittestapp.base;

import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.activity.FirstActivity;
import com.keepsolid.gittestapp.activity.FourthActivity;
import com.keepsolid.gittestapp.activity.ThirdActivity;

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


    public MaterialToolbar getToolbar() {
        return toolbar;
    }

    private void initMenu() {
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.smartphone_btn_chooser: {
                        Intent viewIntent = new Intent(BaseActivity.this, FirstActivity.class);
                        startActivity(viewIntent);
                        return true;
                    }

                    case R.id.smartphone_tab_chooser: {
                        Intent viewIntent = new Intent(BaseActivity.this, ThirdActivity.class);
                        startActivity(viewIntent);
                        return true;
                    }

                    case R.id.smartphone_btn_chooser_manager: {
                        Intent viewIntent = new Intent(BaseActivity.this, FourthActivity.class);
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