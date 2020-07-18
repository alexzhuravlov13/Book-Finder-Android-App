package com.keepsolid.gittestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Smartphone implements Parcelable {
    private String manufacturer;
    private String model;
    private int year;
    private int image;

    public Smartphone() {
    }

    public Smartphone(String manufacturer, String model, int year, int image) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Smartphone that = (Smartphone) o;
        return year == that.year &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(model, that.model) &&
                Objects.equals(image, that.image);
    }

    @Override
    public String toString() {
        return manufacturer + " " + model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, year, image);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    protected Smartphone(Parcel in) {
        manufacturer = in.readString();
        model = in.readString();
        year = in.readInt();
        image = in.readInt();
    }

    public static final Creator<Smartphone> CREATOR = new Creator<Smartphone>() {
        @Override
        public Smartphone createFromParcel(Parcel in) {
            return new Smartphone(in);
        }

        @Override
        public Smartphone[] newArray(int size) {
            return new Smartphone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(manufacturer);
        parcel.writeString(model);
        parcel.writeInt(year);
        parcel.writeInt(image);
    }
}
