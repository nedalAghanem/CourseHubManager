package com.example.coursehubmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

public interface EnrollmentsDao {
    @Insert
    void insertEnrollment(EnrollmentsDao enrollment);

    @Delete
    void deleteEnrollment(EnrollmentsDao enrollment);

    @Query("Select * From Enrollments order by enrollment_id asc")
    LiveData<List<EnrollmentsDao>> getAllEnrollments();

    @Query("SELECT * FROM Enrollments WHERE enrollment_id = :enrollmentId")
    EnrollmentsDao getEnrollmentById(int enrollmentId);


}
