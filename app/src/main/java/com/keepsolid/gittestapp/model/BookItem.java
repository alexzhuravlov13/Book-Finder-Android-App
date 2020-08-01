package com.keepsolid.gittestapp.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.keepsolid.gittestapp.utils.db.ListConverter;

import java.util.Objects;

@Entity(tableName = "bookItemsTable")
@TypeConverters({ListConverter.class})
public class BookItem {

    @PrimaryKey(autoGenerate = true)
    private int bookId;

    private String bookItemId;

    @Embedded
    private VolumeItem volumeInfo;

    public BookItem() {
    }

    public BookItem(int bookId, String bookItemId, VolumeItem volumeInfo) {
        this.bookId = bookId;
        this.bookItemId = bookItemId;
        this.volumeInfo = volumeInfo;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(String bookItemId) {
        this.bookItemId = bookItemId;
    }

    public VolumeItem getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeItem volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return bookId == bookItem.bookId &&
                Objects.equals(bookItemId, bookItem.bookItemId) &&
                Objects.equals(volumeInfo, bookItem.volumeInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookItemId, volumeInfo);
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "bookId=" + bookId +
                ", bookItemId='" + bookItemId + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
