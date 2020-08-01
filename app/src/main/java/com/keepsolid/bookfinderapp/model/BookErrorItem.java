package com.keepsolid.bookfinderapp.model;

public class BookErrorItem {
    private String message;

    public BookErrorItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
