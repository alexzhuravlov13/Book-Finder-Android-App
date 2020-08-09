package com.keepsolid.bookfinderapp.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.base.BaseActivity;
import com.keepsolid.bookfinderapp.fragment.ViewerFragment;
import com.keepsolid.bookfinderapp.model.VolumeItem;
import com.keepsolid.bookfinderapp.screens.main.MainFragment;
import com.keepsolid.bookfinderapp.screens.main.MainPresenter;
import com.keepsolid.bookfinderapp.utils.ApplicationSettingsManager;


public class MainActivity extends BaseActivity {
    private MainFragment chooserFragment;
    private ViewerFragment viewerFragment;

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

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_chooser_container) != null) {
            chooserFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser_container);
        } else {
            chooserFragment = new MainFragment();
        }
        if (chooserFragment != null) {
            chooserFragment.setPresenter(mainPresenter);
        }
        getSupportFragmentManager().beginTransaction().replace(chooserFragmentContainer.getId(), chooserFragment).commit();


        isInLandscapeMode = findViewById(R.id.fragment_viewer) != null;


        if (isInLandscapeMode) {
            viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
            if (viewerFragment != null) {
                viewerFragment.hideOpenButton();
            }
        }

    }

    public void displaySelected(VolumeItem volumeItem) {
        if (isInLandscapeMode) {
            MaterialToolbar toolbar = getToolbar();
            toolbar.setTitle(volumeItem.getTitle());
            viewerFragment.setVolumeItem(volumeItem);
            viewerFragment.showOpenButton();
            viewerFragment.displayResource(volumeItem);
        }
    }

    public boolean isInLandscapeMode() {
        return isInLandscapeMode;
    }
}