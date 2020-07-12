package com.keepsolid.gittestapp.base;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.keepsolid.gittestapp.FirstActivity;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.SecondActivity;
import com.keepsolid.gittestapp.utils.Constants;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

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
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.first_activity_select: {
                        Intent intent = new Intent(BaseActivity.this, FirstActivity.class);
                        startActivity(intent);
                        return true;
                    }

                    case R.id.second_activity_select: {
                        Intent intent = new Intent(BaseActivity.this, SecondActivity.class);
                        startActivityForResult(intent, Constants.TEXT_REQUEST_CODE);
                        return true;
                    }

                    case R.id.about: {
                        String text = getString(R.string.about_text);
                        Intent intent = new Intent(BaseActivity.this, SecondActivity.class);
                        intent.putExtra(Constants.EXTRA_TEXT, text);
                        startActivity(intent);
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }
}