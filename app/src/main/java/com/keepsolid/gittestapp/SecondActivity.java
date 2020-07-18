package com.keepsolid.gittestapp;

import android.os.Bundle;

import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.fragment.ViewerFragment;
import com.keepsolid.gittestapp.utils.Constants;

public class SecondActivity extends BaseActivity {
    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbar(getString(R.string.app_name));

        int smartPhoneId = getIntent().getIntExtra(Constants.KEY_RES_ID, -1);

        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);

        viewerFragment.displayResource(smartPhoneId);

    }
}