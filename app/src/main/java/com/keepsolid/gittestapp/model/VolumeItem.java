package com.keepsolid.gittestapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VolumeItem implements Parcelable {
    private String title;
    private String subtitle;
    private String publisher;
    private List<String> authors = new ArrayList<>();
    private String publishedDate;
    private String description;
    private int pageCount;

    private ImageLinks imageLinks = new ImageLinks("", "");

    public VolumeItem() {
    }

    public VolumeItem(String title, String subtitle, String publisher, List<String> authors, String publishedDate, String description, int pageCount, ImageLinks imageLinks) {
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        if (imageLinks!=null){
            this.imageLinks = imageLinks;
        };
    }


    protected VolumeItem(Parcel in) {
        title = in.readString();
        subtitle = in.readString();
        publisher = in.readString();
        authors = in.createStringArrayList();
        publishedDate = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        imageLinks = in.readParcelable(ImageLinks.class.getClassLoader());
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


    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "VolumeItem{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", imageLinks=" + imageLinks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeItem that = (VolumeItem) o;
        return pageCount == that.pageCount &&
                Objects.equals(title, that.title) &&
                Objects.equals(subtitle, that.subtitle) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(publishedDate, that.publishedDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(imageLinks, that.imageLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, subtitle, publisher, authors, publishedDate, description, pageCount, imageLinks);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeString(publisher);
        parcel.writeStringList(authors);
        parcel.writeString(publishedDate);
        parcel.writeString(description);
        parcel.writeInt(pageCount);
        parcel.writeParcelable(imageLinks, i);
    }
}
