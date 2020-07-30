package com.keepsolid.gittestapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VolumeItem {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private ImageLinks imageLinks;

    public VolumeItem() {
    }

    public VolumeItem(String title, List<String> authors, String publishedDate, ImageLinks imageLinks) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.imageLinks = imageLinks;
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

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    @Override
    public String toString() {
        return "VolumeItem{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", imageLinks=" + imageLinks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeItem that = (VolumeItem) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(publishedDate, that.publishedDate) &&
                Objects.equals(imageLinks, that.imageLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, publishedDate, imageLinks);
    }
}
