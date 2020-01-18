package com.nbu.rimichka.ui.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nbu.rimichka.models.RhymeResponse;
import com.nbu.rimichka.networking.RimichkaApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<ArrayList<RhymeResponse>> rhymeList;

    public SearchViewModel() {
        rhymeList = new MutableLiveData<ArrayList<RhymeResponse>>();
        rhymeList.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<RhymeResponse>> getRhymeList() {
        return rhymeList;
    }

    void executeSearch(String query) {
        Call<List<RhymeResponse>> apiCall = RimichkaApi.getInstance().getService().fetchRhymesAsync(query);

        apiCall.enqueue(new Callback<List<RhymeResponse>>() {
            @Override
            public void onResponse(Call<List<RhymeResponse>> call, Response<List<RhymeResponse>> response) {
                rhymeList.setValue(new ArrayList<RhymeResponse>(response.body()));
                Log.e("Success: ","Rhymes response was saved!");
            }

            @Override
            public void onFailure(Call<List<RhymeResponse>> call, Throwable t) {
                call.cancel();
            }
        });
    }

}