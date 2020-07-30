package com.keepsolid.gittestapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookItem {
    private String id;
    private VolumeItem volumeInfo;

    public BookItem() {
    }

    public BookItem(String id, VolumeItem volumeInfo) {
        this.id = id;
        this.volumeInfo = volumeInfo;
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "id='" + id + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return Objects.equals(id, bookItem.id) &&
                Objects.equals(volumeInfo, bookItem.volumeInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volumeInfo);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeItem getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeItem volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
