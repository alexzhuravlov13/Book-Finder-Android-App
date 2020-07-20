package com.keepsolid.gittestapp.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.fragment.ChooserFragment;
import com.keepsolid.gittestapp.fragment.ViewerFragment;
import com.keepsolid.gittestapp.utils.listeners.SmartphoneSelectListener;

public class FourthActivity extends BaseActivity {

    private ChooserFragment chooserFragment;
    private SmartphoneSelectListener smartphoneSelectListener;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initToolbar(getString(R.string.app_name));

        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);

        container = findViewById(R.id.container);

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
        ViewerFragment viewerFragment = ViewerFragment.newInstance(selectedImageResId);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.container, viewerFragment);
        fragmentTransaction.commit();
    }
}