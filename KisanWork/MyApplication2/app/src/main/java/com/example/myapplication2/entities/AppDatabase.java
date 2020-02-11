package com.example.myapplication2.entities;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase appDatabase;

    public static AppDatabase gettDatabase(final Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "history_database").build();
                }
            }
        }
        return appDatabase;
    }

    public abstract HistoryDao historyDao();
}
