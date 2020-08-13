package com.keepsolid.bookfinderapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.base.BaseActivity;
import com.keepsolid.bookfinderapp.model.VolumeItem;
import com.keepsolid.bookfinderapp.screens.detail.DetailFragment;
import com.keepsolid.bookfinderapp.screens.detail.DetailPresenter;
import com.keepsolid.bookfinderapp.screens.main.MainFragment;
import com.keepsolid.bookfinderapp.screens.main.MainPresenter;
import com.keepsolid.bookfinderapp.utils.ApplicationSettingsManager;


public class MainActivity extends BaseActivity {
    private MainFragment chooserFragment;
    private DetailFragment viewerFragment;

    private FrameLayout fragmentViewerContainer;

    private boolean isInLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar("Book finder");
        initMainMenu();

        initViews();

        showSnackBar("Books list loaded from cache");

    }

    private void initViews() {
        FrameLayout chooserFragmentContainer = findViewById(R.id.fragment_chooser_container);

        MainPresenter mainPresenter = new MainPresenter(new ApplicationSettingsManager(MainActivity.this), getDatabase());

        chooserFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser_container);
        if (chooserFragment == null) {
            chooserFragment = new MainFragment();
        }

        chooserFragment.setPresenter(mainPresenter);
        getSupportFragmentManager().beginTransaction().replace(chooserFragmentContainer.getId(), chooserFragment).commit();

        fragmentViewerContainer = findViewById(R.id.fragment_viewer_container);
        isInLandscapeMode = fragmentViewerContainer != null;


        if (isInLandscapeMode) {
            fragmentViewerContainer.setVisibility(View.INVISIBLE);
            DetailPresenter detailPresenter = new DetailPresenter();

            viewerFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer_container);
            if (viewerFragment == null) {
                viewerFragment = new DetailFragment();
            }
            viewerFragment.setPresenter(detailPresenter);
            getSupportFragmentManager().beginTransaction().replace(fragmentViewerContainer.getId(), viewerFragment).commit();
        }

    }

    public void displaySelected(VolumeItem volumeItem) {
        if (isInLandscapeMode) {
            fragmentViewerContainer.setVisibility(View.VISIBLE);
            MaterialToolbar toolbar = getToolbar();
            toolbar.setTitle(volumeItem.getTitle());
            DetailPresenter detailPresenter = new DetailPresenter();

            viewerFragment = new DetailFragment(volumeItem, detailPresenter);

            getSupportFragmentManager().beginTransaction().replace(fragmentViewerContainer.getId(), viewerFragment).commit();

        }
    }

    public boolean isInLandscapeMode() {
        return isInLandscapeMode;
    }
}