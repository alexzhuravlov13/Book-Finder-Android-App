package com.keepsolid.gittestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleBooksResponse {
    private String kind;
    private long totalItems;

    @SerializedName("items")
    private List<BookItem> bookItems;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }
}
