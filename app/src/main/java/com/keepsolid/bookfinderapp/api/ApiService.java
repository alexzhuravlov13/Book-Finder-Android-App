package com.keepsolid.bookfinderapp.api;

import com.keepsolid.bookfinderapp.model.GoogleBooksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/books/v1/volumes")
    Call<GoogleBooksResponse> getBooks(@Query("q") String query);
}
