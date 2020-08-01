package com.keepsolid.bookfinderapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.fragment.ChooserFragment;
import com.keepsolid.bookfinderapp.fragment.ViewerFragment;
import com.keepsolid.bookfinderapp.model.VolumeItem;
import com.keepsolid.bookfinderapp.utils.Constants;
import com.keepsolid.bookfinderapp.utils.listeners.OnBookRecyclerItemClickListener;


public class MainActivity extends BaseActivity {
    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;

    private OnBookRecyclerItemClickListener listener;

    private boolean isInLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar("Book finder");
        initMainMenu();

        initViews();

        initListeners();

        String historyString = getIntent().getStringExtra(Constants.KEY_RES_ID);
        chooserFragment.setUserInputAndFind(historyString);

        showSnackBar("Books list loaded from cache");
    }

    private void initViews() {
        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);
        isInLandscapeMode = findViewById(R.id.fragment_viewer) != null;

        if (isInLandscapeMode) {
            viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
        }

    }

    private void initListeners() {
        listener = new OnBookRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, VolumeItem volumeItem) {
                displaySelected(volumeItem);
            }
        };
        chooserFragment.setBookSelectListener(listener);
    }

    private void displaySelected(VolumeItem volumeItem) {
        if (isInLandscapeMode) {
            MaterialToolbar toolbar = getToolbar();
            toolbar.setTitle(volumeItem.getTitle());
            viewerFragment.displayResource(volumeItem);
        } else {
            Intent viewIntent = new Intent(MainActivity.this, DetailActivity.class);
            viewIntent.putExtra(Constants.KEY_RES_ID, volumeItem);
            startActivity(viewIntent);
        }
    }
}