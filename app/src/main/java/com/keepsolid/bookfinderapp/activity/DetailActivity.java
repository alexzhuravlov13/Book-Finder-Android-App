package com.keepsolid.bookfinderapp.activity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.fragment.ViewerFragment;
import com.keepsolid.bookfinderapp.model.VolumeItem;
import com.keepsolid.bookfinderapp.utils.Constants;

public class DetailActivity extends BaseActivity {
    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initToolbar(getString(R.string.app_name));
        initMainMenu();
        enableUpButton();

        VolumeItem volumeItem = getIntent().getParcelableExtra(Constants.KEY_RES_ID);


        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);

        if (viewerFragment != null) {
            MaterialToolbar toolbar = getToolbar();
            toolbar.setTitle(volumeItem.getTitle());
            viewerFragment.setVolumeItem(volumeItem);
            viewerFragment.displayResource(volumeItem);
        }

    }
}