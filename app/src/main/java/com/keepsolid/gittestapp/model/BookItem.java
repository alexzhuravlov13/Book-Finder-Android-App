package com.keepsolid.gittestapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookItem {
    private int id;
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String thumbnail;

    public BookItem() {
        this.authors = new ArrayList<>();
    }

    public BookItem(int id, String title, List<String> authors, String publishedDate, String thumbnail) {
        this.id = id;
        this.title = title;
        if (authors == null) {
            this.authors = new ArrayList<>();
        } else {
            this.authors = authors;
        }
        this.publishedDate = publishedDate;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return id == bookItem.id &&
                Objects.equals(title, bookItem.title) &&
                Objects.equals(authors, bookItem.authors) &&
                Objects.equals(publishedDate, bookItem.publishedDate) &&
                Objects.equals(thumbnail, bookItem.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authors, publishedDate, thumbnail);
    }
}
