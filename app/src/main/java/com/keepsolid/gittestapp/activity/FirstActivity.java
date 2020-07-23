package com.keepsolid.gittestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.adapter.SmartphoneRecyclerAdapter;
import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.fragment.ChooserFragment;
import com.keepsolid.gittestapp.fragment.ViewerFragment;
import com.keepsolid.gittestapp.model.Smartphone;
import com.keepsolid.gittestapp.utils.Constants;
import com.keepsolid.gittestapp.utils.listeners.OnSmartphoneRecyclerItemClickListener;
import com.keepsolid.gittestapp.utils.repository.SmartphoneRepository;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends BaseActivity {
    private ChooserFragment chooserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initToolbar(getString(R.string.app_name));

        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);


    }

}