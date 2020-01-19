package com.nbu.rimichka.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.nbu.rimichka.database.RhymePairsDao;
import com.nbu.rimichka.database.RhymePairsDatabase;
import com.nbu.rimichka.models.RhymePair;

import java.util.List;

public class RhymePairsRepository {

    private RhymePairsDao rhymePairsDao;
    private LiveData<List<RhymePair>> allPairs;

    public RhymePairsRepository(Application application) {
        RhymePairsDatabase db = RhymePairsDatabase.getDatabase(application);
        rhymePairsDao = db.rhymePairsDao();
        allPairs = rhymePairsDao.getAll();
    }

    public LiveData<List<RhymePair>> getAllPairs() {
        return allPairs;
    }

    public void insert(RhymePair pair) {
        RhymePairsDatabase.databaseWriteExecutor.execute(() -> {
            rhymePairsDao.insert(pair);
        });
    }

    public void delete(RhymePair pair) {
        RhymePairsDatabase.databaseWriteExecutor.execute(() -> {
            rhymePairsDao.delete(pair);
        });
    }
}
