package com.example.coursehubmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.coursehubmanager.database.entity.Enrollments;

import java.util.List;
@Dao
public interface EnrollmentsDao {

    @Insert
    void insertEnrollment(Enrollments enrollment);

    @Delete
    void deleteEnrollment(Enrollments enrollment);

    @Query("Select * From Enrollments order by enrollment_id asc")
    LiveData<List<Enrollments>> getAllEnrollments();

    @Query("SELECT * FROM Enrollments WHERE enrollment_id = :enrollmentId")
    Enrollments getEnrollmentById(int enrollmentId);


}
