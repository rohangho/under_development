package com.example.myapplication2.entities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM History")
    List<History> getAll();

    @Insert
    void insertAppointment(History abc);

}
