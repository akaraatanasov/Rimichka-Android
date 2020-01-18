package com.nbu.rimichka.networking;

import com.nbu.rimichka.models.RhymeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RimichkaApiService  {
    @GET("/?json=1")
    Call<List<RhymeResponse>> fetchRhymesAsync(@Query("word") String word);
}