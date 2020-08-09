package com.keepsolid.bookfinderapp.screens.main;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.keepsolid.bookfinderapp.api.ApiCallback;
import com.keepsolid.bookfinderapp.api.RestClient;
import com.keepsolid.bookfinderapp.model.BookErrorItem;
import com.keepsolid.bookfinderapp.model.BookItem;
import com.keepsolid.bookfinderapp.model.GoogleBooksResponse;
import com.keepsolid.bookfinderapp.utils.ApplicationSettingsManager;
import com.keepsolid.bookfinderapp.utils.db.AppDatabase;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private ApplicationSettingsManager applicationSettingsManager;
    private AppDatabase appDatabase;
    private LiveData<List<BookItem>> liveData;

    public MainPresenter(ApplicationSettingsManager applicationSettingsManager, AppDatabase database) {
        this.applicationSettingsManager = applicationSettingsManager;
        this.appDatabase = database;
    }

    @Override
    public void takeView(MainContract.View view) {

        this.view = view;

        liveData = appDatabase.bookItemDao().getAll();
        view.observeItems(liveData);

    }

    @Override
    public void dropView() {
        view.stopObserving(liveData);
        this.view = null;
        liveData = null;
    }

    @Override
    public void performSearch(String userInputText) {

        if (userInputText.trim().isEmpty()) {
            view.showInputError();
            return;
        }

        view.hideKeyboard();
        view.showProgressBlock();

        String searchText;
        searchText = userInputText.trim();
        if (!searchText.isEmpty()) {
            List<String> cachedItems = applicationSettingsManager.getCachedItems();
            LinkedList<String> historyItems = new LinkedList<>();
            if (cachedItems != null) {
                historyItems.addAll(cachedItems);
            }
            historyItems.addFirst(searchText);
            applicationSettingsManager.cacheLoadedItems(historyItems);

            OnRequestFinishedListener onRequestFinishedListener = new OnRequestFinishedListener() {
                @Override
                public void onRequestFinished(@Nullable BookErrorItem errorItem) {
                    view.hideProgressBlock();
                    if (errorItem != null) {
                        view.showRequestError(errorItem.getMessage());
                    }
                }
            };

            loadBooks(searchText, onRequestFinishedListener);
        } else view.makeErrorToast("Put any text");

    }

    @Override
    public void loadBooks(String title, OnRequestFinishedListener onRequestFinishedListener) {
        RestClient.getInstance().getApiService().getBooks(title).enqueue(new ApiCallback<GoogleBooksResponse>() {

            @Override
            public void success(Response<GoogleBooksResponse> response) {
                List<BookItem> bookItems = response.body().getBookItems();
                cacheItems(bookItems);
                if (onRequestFinishedListener != null) {
                    onRequestFinishedListener.onRequestFinished(null);
                }
            }

            @Override
            public void failure(BookErrorItem bookErrorItem) {
                if (onRequestFinishedListener != null) {
                    onRequestFinishedListener.onRequestFinished(bookErrorItem);
                }
            }
        });
    }


    private void cacheItems(List<BookItem> items) {
        appDatabase.bookItemDao().deleteAll();
        appDatabase.bookItemDao().insert(items);
    }

    interface OnRequestFinishedListener {

        void onRequestFinished(@Nullable BookErrorItem errorItem);

    }


}
