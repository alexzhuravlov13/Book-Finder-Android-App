package com.keepsolid.bookfinderapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.model.ImageLinks;
import com.keepsolid.bookfinderapp.model.VolumeItem;

import java.util.List;

public class ViewerFragment extends Fragment {

    private AppCompatImageView thumbnail;
    private TextView title;

    private LinearLayout authors_layout;
    private TextView authors;

    private LinearLayout publishedDate_layout;
    private TextView publishedDate;

    private LinearLayout publisher_layout;
    private TextView publisher;

    private LinearLayout pages_layout;
    private TextView pages;

    private LinearLayout description_layout;
    private TextView description;

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

        authors_layout = view.findViewById(R.id.authors_layout);
        authors = view.findViewById(R.id.authors);

        publishedDate_layout = view.findViewById(R.id.publishedDate_layout);
        publishedDate = view.findViewById(R.id.publishedDate);

        publisher_layout = view.findViewById(R.id.publisher_layout);
        publisher = view.findViewById(R.id.publisher);

        pages_layout = view.findViewById(R.id.pages_count_layout);
        pages = view.findViewById(R.id.pages_count);


        description_layout = view.findViewById(R.id.description_layout);
        description = view.findViewById(R.id.description);

        return view;
    }

    public void displayResource(VolumeItem volumeItem) {

        Log.i("VOLUMEITEM_LOG", volumeItem.toString());

        ImageLinks imageLinks = volumeItem.getImageLinks();
        if (imageLinks != null && imageLinks.getThumbnail() != null) {
            Glide.with(thumbnail.getContext()).load(imageLinks.getThumbnail()).placeholder(R.drawable.book).into(thumbnail);
        } else {
            Glide.with(thumbnail.getContext()).load(R.drawable.book).into(thumbnail);
        }

        thumbnail.setContentDescription(volumeItem.getTitle());

        title.setText(volumeItem.getTitle());

        List<String> volumeItemAuthors = volumeItem.getAuthors();
        if (volumeItemAuthors == null || volumeItemAuthors.size() == 0) {
            authors.setText(R.string.unknown);
        } else {
            authors_layout.setVisibility(View.VISIBLE);
            String authorsString = volumeItemAuthors.toString();
            authors.setText(authorsString.substring(1, authorsString.length() - 1));
        }

        String volumePublishedDate = volumeItem.getPublishedDate();
        if (volumePublishedDate == null || volumePublishedDate.trim().isEmpty()) {
            publishedDate.setText(R.string.unknown);
        } else {
            publishedDate_layout.setVisibility(View.VISIBLE);
            String year = volumePublishedDate.split("-")[0];
            publishedDate.setText(year);

        }

        String volumeItemPublisher = volumeItem.getPublisher();
        if (volumeItemPublisher == null || volumeItemPublisher.trim().isEmpty()) {
            publisher.setText(R.string.unknown);
        } else {
            publisher_layout.setVisibility(View.VISIBLE);
            publisher.setText(volumeItemPublisher);
        }

        int volumeItemPageCount = volumeItem.getPageCount();
        if (volumeItemPageCount == 0) {
            pages.setText(R.string.unknown);
        } else {
            pages.setText(String.valueOf(volumeItemPageCount));
        }


        String volumeItemDescription = volumeItem.getDescription();
        if (volumeItemDescription == null || volumeItemDescription.trim().isEmpty()) {
            description_layout.setVisibility(View.INVISIBLE);
        } else {
            description_layout.setVisibility(View.VISIBLE);
            description.setText(volumeItemDescription);
        }

    }

    public AppCompatImageView getThumbnail() {
        return thumbnail;
    }

}