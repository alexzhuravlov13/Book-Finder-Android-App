package com.keepsolid.bookfinderapp.utils.db;

import android.net.Uri;

import androidx.room.TypeConverter;

public class UriConverter {

    @TypeConverter
    public String fromUri(Uri uri) {
        return uri.toString();
    }

    @TypeConverter
    public Uri toUri(String uriString) {
        return Uri.parse(uriString);
    }

}
