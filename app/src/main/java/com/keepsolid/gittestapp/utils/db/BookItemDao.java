package com.keepsolid.gittestapp.utils.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.keepsolid.gittestapp.model.BookItem;

import java.util.List;

@Dao
public interface BookItemDao {

    @Query("SELECT * FROM bookItemsTable")
    LiveData<List<BookItem>> getAll();

    @Query("SELECT * FROM bookItemsTable WHERE bookId = :id")
    LiveData<BookItem> getById(long id);

    @Insert
    void insert(BookItem item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<BookItem> items);

    @Update
    void update(BookItem item);

    @Delete
    void delete(BookItem item);

    @Query("DELETE FROM bookItemsTable")
    void deleteAll();
}
