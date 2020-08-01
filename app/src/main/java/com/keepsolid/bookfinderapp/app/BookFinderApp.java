package com.keepsolid.bookfinderapp.app;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.keepsolid.bookfinderapp.utils.db.AppDatabase;

public class BookFinderApp extends Application {

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "bookitems")
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
