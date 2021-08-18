package com.example.spacex;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CrewModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CrewDao crewDao();
}
