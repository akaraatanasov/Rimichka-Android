package com.nbu.rimichka.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity(tableName = "rhyme_pairs")
public class RhymePair {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    @NonNull
    @ColumnInfo(name = "rhyme")
    private String rhyme;

    public RhymePair(int id, @NonNull String word, @NonNull String rhyme) {
        this.id = id;
        this.word = word;
        this.rhyme = rhyme;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getRhyme() {
        return rhyme;
    }
}
