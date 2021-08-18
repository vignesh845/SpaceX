package com.example.spacex;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrewDao {
    @Query("SELECT * FROM crew")
    List<CrewModel> getAll();

    @Insert
    void insertAll(List<CrewModel> crewModels);

    @Query("DELETE FROM crew")
    void deleteAll();
}
