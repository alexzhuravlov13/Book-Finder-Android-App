package com.keepsolid.bookfinderapp.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgressBlock();

    void hideProgressBlock();

    void makeErrorToast(String errorMessage);

    void hideKeyboard();

}