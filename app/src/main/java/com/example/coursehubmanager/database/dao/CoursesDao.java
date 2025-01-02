package com.example.coursehubmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.database.entity.Lessons;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface CoursesDao {

    @Insert
    void insertCourses(Courses... course);

    @Insert
    long[] insertFakeCourses(ArrayList<Courses> courses);

    @Update
    void updateCourses(Courses course);

    @Delete
    void deleteCourses(Courses course);

    @Query("SELECT * FROM Courses WHERE course_id = :courseId")
    Courses getCourseById(int courseId);

    @Query("Select * From Courses order by course_name asc")
    LiveData<List<Courses>> getAllCourses();

    @Query("Select * From Courses  Where course_name like '%' || :courseName || '%'")
    LiveData<List<Courses>> searchByCourseName(String courseName);












}
