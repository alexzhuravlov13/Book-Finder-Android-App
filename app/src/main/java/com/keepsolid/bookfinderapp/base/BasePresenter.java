package com.keepsolid.bookfinderapp.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}