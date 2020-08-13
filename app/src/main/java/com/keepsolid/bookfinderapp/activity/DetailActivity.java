package com.keepsolid.bookfinderapp.activity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.base.BaseActivity;
import com.keepsolid.bookfinderapp.model.VolumeItem;
import com.keepsolid.bookfinderapp.screens.detail.DetailFragment;
import com.keepsolid.bookfinderapp.screens.detail.DetailPresenter;
import com.keepsolid.bookfinderapp.utils.Constants;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initToolbar(getString(R.string.app_name));
        initMainMenu();
        enableUpButton();

        VolumeItem volumeItem = getIntent().getParcelableExtra(Constants.KEY_RES_ID);

        DetailPresenter detailPresenter = new DetailPresenter();

        View container = findViewById(R.id.fragment_viewer_container);
        DetailFragment detailFragment = new DetailFragment();

        detailFragment.setPresenter(detailPresenter);
        getSupportFragmentManager().beginTransaction().replace(container.getId(), detailFragment).commit();

        MaterialToolbar toolbar = getToolbar();
        if (volumeItem != null) {
            toolbar.setTitle(volumeItem.getTitle());
            detailFragment.setVolumeItem(volumeItem);
        }

    }
}