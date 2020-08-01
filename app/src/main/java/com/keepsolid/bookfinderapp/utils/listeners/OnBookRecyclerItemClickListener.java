package com.keepsolid.bookfinderapp.utils.listeners;

import android.view.View;

import com.keepsolid.bookfinderapp.model.VolumeItem;

public interface OnBookRecyclerItemClickListener {
    void onItemClick(View v, int position, VolumeItem volumeItem);
}
