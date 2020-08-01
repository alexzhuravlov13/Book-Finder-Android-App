package com.keepsolid.bookfinderapp.utils.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.keepsolid.bookfinderapp.model.BookItem;

@Database(entities = {BookItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookItemDao bookItemDao();
}
