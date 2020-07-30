package com.keepsolid.gittestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class VolumeItem implements Parcelable {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private ImageLinks imageLinks = new ImageLinks("", "");

    public VolumeItem() {
    }

    public VolumeItem(String title, List<String> authors, String publishedDate, ImageLinks imageLinks) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        if (imageLinks!=null){
            this.imageLinks = imageLinks;
        }
        this.imageLinks = imageLinks;
    }

    protected VolumeItem(Parcel in) {
        title = in.readString();
        authors = in.createStringArrayList();
        publishedDate = in.readString();
    }

    public static final Creator<VolumeItem> CREATOR = new Creator<VolumeItem>() {
        @Override
        public VolumeItem createFromParcel(Parcel in) {
            return new VolumeItem(in);
        }

        @Override
        public VolumeItem[] newArray(int size) {
            return new VolumeItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeStringList(authors);
        parcel.writeString(publishedDate);
    }
}
