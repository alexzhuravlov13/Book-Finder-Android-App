package com.keepsolid.gittestapp.utils.listeners;

import android.net.Uri;
import android.view.View;

import com.keepsolid.gittestapp.model.VolumeItem;

public interface OnBookRecyclerItemClickListener {
    public void onItemClick(View v, int position, VolumeItem volumeItem);
}
