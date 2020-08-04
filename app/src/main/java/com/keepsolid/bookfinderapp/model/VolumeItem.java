package com.keepsolid.bookfinderapp.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.keepsolid.bookfinderapp.utils.db.ListConverter;
import com.keepsolid.bookfinderapp.utils.db.UriConverter;

import java.util.List;
import java.util.Objects;

@Entity
@TypeConverters({ListConverter.class, UriConverter.class})
public class VolumeItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int volumeId;
    private String title;
    private String subtitle;
    private String publisher;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private Uri previewLink;
    private int pageCount;

    @Embedded
    private ImageLinks imageLinks;

    public VolumeItem() {
    }

    protected VolumeItem(Parcel in) {
        volumeId = in.readInt();
        title = in.readString();
        subtitle = in.readString();
        publisher = in.readString();
        authors = in.createStringArrayList();
        publishedDate = in.readString();
        description = in.readString();
        previewLink = in.readParcelable(Uri.class.getClassLoader());
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

    public Uri getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(Uri previewLink) {
        this.previewLink = previewLink;
    }

    public int getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(int volumeId) {
        this.volumeId = volumeId;
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
                "volumeId=" + volumeId +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", previewLink=" + previewLink +
                ", pageCount=" + pageCount +
                ", imageLinks=" + imageLinks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeItem that = (VolumeItem) o;
        return volumeId == that.volumeId &&
                pageCount == that.pageCount &&
                Objects.equals(title, that.title) &&
                Objects.equals(subtitle, that.subtitle) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(publishedDate, that.publishedDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(previewLink, that.previewLink) &&
                Objects.equals(imageLinks, that.imageLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volumeId, title, subtitle, publisher, authors, publishedDate, description, previewLink, pageCount, imageLinks);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(volumeId);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeString(publisher);
        parcel.writeStringList(authors);
        parcel.writeString(publishedDate);
        parcel.writeString(description);
        parcel.writeParcelable(previewLink, i);
        parcel.writeInt(pageCount);
        parcel.writeParcelable(imageLinks, i);
    }
}
