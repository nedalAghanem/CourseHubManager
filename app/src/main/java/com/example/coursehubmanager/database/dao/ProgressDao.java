package com.example.coursehubmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursehubmanager.database.entity.Progress;
@Dao
public interface ProgressDao {

    @Insert
    void insertProgress(Progress progress);

    @Query("SELECT * FROM Progress WHERE progres_id = :progresId")
    Progress getProgressById(int progresId);

}
