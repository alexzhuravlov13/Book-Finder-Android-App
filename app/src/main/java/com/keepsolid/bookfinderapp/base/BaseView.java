package com.keepsolid.bookfinderapp.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgressBlock();

    void hideProgressBlock();

    void hideKeyboard();

    void makeErrorToast(String errorMessage);


}