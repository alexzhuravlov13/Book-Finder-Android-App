package com.keepsolid.bookfinderapp.screens.detail;

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View view;

    public DetailPresenter() {
    }

    @Override
    public void takeView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
