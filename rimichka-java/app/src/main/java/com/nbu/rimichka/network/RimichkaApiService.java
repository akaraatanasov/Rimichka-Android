package com.nbu.rimichka.network;

import com.nbu.rimichka.models.Rhyme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RimichkaApiService  {
    @GET("/?json=1")
    Call<List<Rhyme>> fetchRhymesAsync(@Query("word") String word);
}