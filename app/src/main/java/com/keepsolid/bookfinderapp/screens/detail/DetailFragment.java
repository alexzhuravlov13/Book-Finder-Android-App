package com.keepsolid.bookfinderapp.screens.detail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.model.ImageLinks;
import com.keepsolid.bookfinderapp.model.VolumeItem;

import java.util.List;

public class DetailFragment extends Fragment implements DetailContract.View {

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

    private FloatingActionButton buttonOpenBrowser;

    private VolumeItem volumeItem;

    private DetailContract.Presenter presenter;

    private View context;

    public DetailFragment() {
    }

    public DetailFragment(VolumeItem volumeItem, DetailContract.Presenter presenter) {
        this.presenter = presenter;
        this.volumeItem = volumeItem;
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

        buttonOpenBrowser = view.findViewById(R.id.btn_open_web);

        buttonOpenBrowser.setOnClickListener(view1 -> {
            try {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, volumeItem.getPreviewLink());
                startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getContext(), "No application can handle this request."
                        + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });
        if (volumeItem != null) {
            displayResource(volumeItem);
        }
        return view;
    }


    public void displayResource(VolumeItem volumeItem) {

        Log.i("VOLUMEITEM_LOG", volumeItem.toString());
        presenter.takeView(this);
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

    public VolumeItem getVolumeItem() {
        return volumeItem;
    }

    public void setVolumeItem(VolumeItem volumeItem) {
        this.volumeItem = volumeItem;
    }

    public void showOpenButton() {
        buttonOpenBrowser.setVisibility(View.VISIBLE);
    }

    public void hideOpenButton() {
        buttonOpenBrowser.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressBlock() {

    }

    @Override
    public void hideProgressBlock() {

    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}