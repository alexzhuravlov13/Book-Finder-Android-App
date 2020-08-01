package com.keepsolid.gittestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import java.util.Objects;

@Entity
public class ImageLinks implements Parcelable {
    private int imageLinksId;
    private String smallThumbnail;
    private String thumbnail;

    public ImageLinks() {
    }

    public ImageLinks(int imageLinksId, String smallThumbnail, String thumbnail) {
        this.imageLinksId = imageLinksId;
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    protected ImageLinks(Parcel in) {
        imageLinksId = in.readInt();
        smallThumbnail = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<ImageLinks> CREATOR = new Creator<ImageLinks>() {
        @Override
        public ImageLinks createFromParcel(Parcel in) {
            return new ImageLinks(in);
        }

        @Override
        public ImageLinks[] newArray(int size) {
            return new ImageLinks[size];
        }
    };

    public int getImageLinksId() {
        return imageLinksId;
    }

    public void setImageLinksId(int imageLinksId) {
        this.imageLinksId = imageLinksId;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageLinks that = (ImageLinks) o;
        return imageLinksId == that.imageLinksId &&
                Objects.equals(smallThumbnail, that.smallThumbnail) &&
                Objects.equals(thumbnail, that.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageLinksId, smallThumbnail, thumbnail);
    }

    @Override
    public String toString() {
        return "ImageLinks{" +
                "id=" + imageLinksId +
                ", smallThumbnail='" + smallThumbnail + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageLinksId);
        parcel.writeString(smallThumbnail);
        parcel.writeString(thumbnail);
    }
}
