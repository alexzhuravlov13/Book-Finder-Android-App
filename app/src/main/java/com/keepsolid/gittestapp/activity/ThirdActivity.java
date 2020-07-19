package com.keepsolid.gittestapp.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.adapter.ViewPagerAdapter;
import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.fragment.ViewerFragment;

public class ThirdActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initToolbar(getString(R.string.app_name));

        initViews();

        initFragments();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setListeners();

    }

    private void initFragments() {
        adapter.addFragment(ViewerFragment.newInstance(0), getString(R.string.htc));
        adapter.addFragment(ViewerFragment.newInstance(1), getString(R.string.moto));
        adapter.addFragment(ViewerFragment.newInstance(2), getString(R.string.pixel));
        adapter.addFragment(ViewerFragment.newInstance(3), getString(R.string.galaxy));
    }

    private void setListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getToolbar().setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
    }
}