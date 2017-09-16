package com.example.android.lagosdevelopers.api;

import com.example.android.lagosdevelopers.model.itemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by OWOEYE on 9/13/2017.
 */

public interface Service {
    @GET("/search/users?q=language:java+location:lagos")
    Call<itemResponse>getItems();
}
