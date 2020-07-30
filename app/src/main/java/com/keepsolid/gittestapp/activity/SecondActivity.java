package com.keepsolid.gittestapp.activity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.fragment.ViewerFragment;
import com.keepsolid.gittestapp.model.VolumeItem;
import com.keepsolid.gittestapp.utils.Constants;

public class SecondActivity extends BaseActivity {
    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbar(getString(R.string.app_name));
        enableUpButton();



        VolumeItem volumeItem = getIntent().getParcelableExtra(Constants.KEY_RES_ID);

        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);

        if (viewerFragment != null) {
            MaterialToolbar toolbar = getToolbar();
            toolbar.setTitle(volumeItem.getTitle());
            viewerFragment.displayResource(volumeItem);
        }

    }
}