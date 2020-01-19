package com.nbu.rimichka.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nbu.rimichka.models.RhymePair;

import java.util.List;

@Dao
public interface RhymePairsDao {

    @Query("SELECT * FROM rhyme_pairs")
    LiveData<List<RhymePair>> getAll();

    @Query("SELECT * FROM rhyme_pairs WHERE id = :id")
    RhymePair getById(int id);

    @Insert
    void insert(RhymePair pair);

    @Delete
    void delete(RhymePair pair);

    @Query("DELETE FROM rhyme_pairs")
    void deleteAll();

}
