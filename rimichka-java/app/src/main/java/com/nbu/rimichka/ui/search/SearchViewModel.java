package com.nbu.rimichka.ui.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nbu.rimichka.models.Rhyme;
import com.nbu.rimichka.network.RimichkaApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Rhyme>> rhymeList;

    public SearchViewModel() {
        rhymeList = new MutableLiveData<ArrayList<Rhyme>>();
        rhymeList.setValue(new ArrayList<>());
    }

    LiveData<ArrayList<Rhyme>> getRhymeList() {
        return rhymeList;
    }

    void executeSearch(String word) {
        Call<List<Rhyme>> apiCall = RimichkaApi.getInstance().getService().fetchRhymesAsync(word);

        apiCall.enqueue(new Callback<List<Rhyme>>() {
            @Override
            public void onResponse(Call<List<Rhyme>> call, Response<List<Rhyme>> response) {
                List<Rhyme> fetchedList = response.body();
                ArrayList<Rhyme> sortedRhymes = listOfRhymesSorted(fetchedList, word);
                rhymeList.setValue(sortedRhymes);
                Log.e("Success: ","Rhymes response was saved!");
            }

            @Override
            public void onFailure(Call<List<Rhyme>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private ArrayList<Rhyme> listOfRhymesSorted(List<Rhyme> fetchedRhymes, String removingWord) {
        List<Rhyme> sortedRhymes = fetchedRhymes;

        Collections.sort(sortedRhymes, new Comparator<Rhyme>() {
            @Override
            public int compare(Rhyme r1, Rhyme r2) {
                return r2.pri - r1.pri;
            }
        });

        ArrayList<Rhyme> filteredRhymes = new ArrayList<Rhyme>();
        for (Rhyme rhyme: sortedRhymes)
            if (!rhyme.wrd.equals(removingWord))
                filteredRhymes.add(rhyme);

        return filteredRhymes;
    }

}