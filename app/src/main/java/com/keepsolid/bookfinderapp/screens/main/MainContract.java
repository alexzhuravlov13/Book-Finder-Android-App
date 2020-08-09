package com.keepsolid.bookfinderapp.screens.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.keepsolid.bookfinderapp.base.BasePresenter;
import com.keepsolid.bookfinderapp.base.BaseView;
import com.keepsolid.bookfinderapp.model.BookItem;

import java.util.List;

public class MainContract {

    interface View extends BaseView<Presenter> {
        void showInputError();

        void showRequestError(@NonNull String message);

        void observeItems(LiveData<List<BookItem>> itemsLiveData);

        void stopObserving(LiveData<List<BookItem>> liveRepoData);

    }

    interface Presenter extends BasePresenter<View> {

        void performSearch(String userInputText);

        void loadBooks(String title, MainPresenter.OnRequestFinishedListener onRequestFinishedListener);

    }

}
