package com.keepsolid.gittestapp;

import android.content.Intent;
import android.os.Bundle;

import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.fragment.ChooserFragment;
import com.keepsolid.gittestapp.fragment.ViewerFragment;
import com.keepsolid.gittestapp.utils.Constants;
import com.keepsolid.gittestapp.utils.listeners.SmartphoneSelectListener;

public class FirstActivity extends BaseActivity {
    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;
    private SmartphoneSelectListener smartphoneSelectListener;
    private boolean inLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initToolbar(getString(R.string.app_name));

        inLandscapeMode = findViewById(R.id.fragment_viewer) != null;


        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);

        if (inLandscapeMode) {
            viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
        }

        smartphoneSelectListener = new SmartphoneSelectListener() {
            @Override
            public void onHtcSelected() {
                displaySelected(0);
            }

            @Override
            public void onMotoSelected() {
                displaySelected(1);
            }

            @Override
            public void onPixelSelected() {
                displaySelected(2);
            }

            @Override
            public void onGalaxySelected() {
                displaySelected(3);
            }
        };

        chooserFragment.setSmartphoneSelectListener(smartphoneSelectListener);

    }

    private void displaySelected(int selectedImageResId) {
        if (inLandscapeMode) {
            viewerFragment.displayResource(selectedImageResId);
        } else {
            Intent viewIntent = new Intent(FirstActivity.this, SecondActivity.class);
            viewIntent.putExtra(Constants.KEY_RES_ID, selectedImageResId);
            startActivity(viewIntent);
        }
    }
}