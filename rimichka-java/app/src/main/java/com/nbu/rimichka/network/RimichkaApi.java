package com.nbu.rimichka.network;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RimichkaApi {

    private static final RimichkaApi apiInstance = new RimichkaApi();
    private static final String BASE_URL = "https://rimichka.com";

    private final Moshi moshi;
    private final Retrofit retrofit;
    private final RimichkaApiService service;

    private RimichkaApi() {
        moshi = new Moshi.Builder().build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build();
        service = retrofit.create(RimichkaApiService.class);
    }

    public static RimichkaApi getInstance() {
        return apiInstance;
    }

    public RimichkaApiService getService() {
        return service;
    }

}
