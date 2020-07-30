package com.keepsolid.gittestapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.model.VolumeItem;

public class ViewerFragment extends Fragment {

    private AppCompatImageView thumbnail;
    private TextView title;
    private TextView authors;
    private TextView publishedDate;
    private VolumeItem volumeItem;

    public ViewerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            volumeItem = savedInstanceState.getParcelable("volumeItem");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bookId", volumeItem);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        thumbnail = view.findViewById(R.id.thumbnail);
        title = view.findViewById(R.id.title);
        authors = view.findViewById(R.id.authors);
        publishedDate = view.findViewById(R.id.publishedDate);
        return view;
    }

    public void displayResource(VolumeItem volumeItem) {

        Log.i("VOLUMEITEM_LOG", volumeItem.toString());


        Glide.with(thumbnail.getContext()).load(volumeItem.getImageLinks().getThumbnail()).placeholder(R.drawable.book).into(thumbnail);
        thumbnail.setContentDescription(volumeItem.getTitle());

        title.setText(volumeItem.getTitle());
        String authorsString = volumeItem.getAuthors().toString();
        authors.setText(authorsString.substring(1, authorsString.length()-1));
        String year = volumeItem.getPublishedDate().split("-")[0];
        publishedDate.setText(year);
    }

    public AppCompatImageView getThumbnail() {
        return thumbnail;
    }
}