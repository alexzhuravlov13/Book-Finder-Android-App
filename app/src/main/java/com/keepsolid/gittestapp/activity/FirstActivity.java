package com.keepsolid.gittestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.fragment.ChooserFragment;
import com.keepsolid.gittestapp.fragment.ViewerFragment;
import com.keepsolid.gittestapp.model.VolumeItem;
import com.keepsolid.gittestapp.utils.Constants;
import com.keepsolid.gittestapp.utils.listeners.OnBookRecyclerItemClickListener;


public class FirstActivity extends BaseActivity {
    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;

    private OnBookRecyclerItemClickListener listener;

    private boolean isInLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initToolbar("Book finder");

        initViews();

        initListeners();
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
            Intent viewIntent = new Intent(FirstActivity.this, SecondActivity.class);
            viewIntent.putExtra(Constants.KEY_RES_ID, volumeItem);
            startActivity(viewIntent);
        }
    }
}