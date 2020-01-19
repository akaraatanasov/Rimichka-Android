package com.nbu.rimichka.database;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nbu.rimichka.models.RhymePair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RhymePair.class}, version = 1, exportSchema = false)
public abstract class RhymePairsDatabase extends RoomDatabase {

    public abstract RhymePairsDao rhymePairsDao();

    private static volatile RhymePairsDatabase dbInstance;
    private static final int threads = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(threads);

    public static RhymePairsDatabase getDatabase(final Context context) {
        if (dbInstance == null) {
            synchronized (RhymePairsDatabase.class) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(context.getApplicationContext(), RhymePairsDatabase.class, "rhyme_pairs_database")
//                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return dbInstance;
    }

    private static RhymePairsDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                RhymePairsDao dao = dbInstance.rhymePairsDao();
                dao.deleteAll();

                dao.insert(new RhymePair(0, "лука", "пролука"));
                dao.insert(new RhymePair(0, "овен", "зелен"));
            });
        }
    };

}
