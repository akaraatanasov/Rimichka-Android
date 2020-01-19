package com.nbu.rimichka.ui.favorites;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nbu.rimichka.models.RhymePair;
import com.nbu.rimichka.repository.RhymePairsRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private RhymePairsRepository repository;

    private LiveData<List<RhymePair>> allRhymePairs;

    public FavoritesViewModel(Application application) {
        super(application);
        repository = new RhymePairsRepository(application);
        allRhymePairs = repository.getAllPairs();
    }

    LiveData<List<RhymePair>> getAllRhymePairs() { return allRhymePairs; }

    void delete(RhymePair pair) { repository.delete(pair); }

}