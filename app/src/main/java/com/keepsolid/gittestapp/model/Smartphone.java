package com.keepsolid.gittestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Smartphone {
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

}
