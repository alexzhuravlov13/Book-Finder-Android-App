package com.keepsolid.bookfinderapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.keepsolid.bookfinderapp.api.RestClient;

import java.lang.reflect.Type;
import java.util.List;

public class ApplicationSettingsManager {
    private Context context;

    public ApplicationSettingsManager(Context context) {
        this.context = context;
    }

    private SharedPreferences getPrefs() {
        return context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void cacheLoadedItems(List<String> items) {
        getPrefs().edit().putString(Constants.PREFS_STRING_LIST, RestClient.getInstance().getGson().toJson(items)).apply();
    }

    public List<String> getCachedItems() {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        String jsonList = getPrefs().getString(Constants.PREFS_STRING_LIST, null);
        if (TextUtils.isEmpty(jsonList)) {
            return null;
        }
        return RestClient.getInstance().getGson().fromJson(jsonList, listType);
    }
}
