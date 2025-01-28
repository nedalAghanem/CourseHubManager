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
public interface LessonsDao {

    @Insert
    void insertLesson(Lessons lesson);
    @Insert
    void insertFakeLesson(ArrayList<Lessons> lessons);

    @Update
    void updateLesson(Lessons lesson);

    @Delete
    void deleteLesson(Lessons lesson);

    @Query("Select * From Lessons order by lesson_id asc")
    LiveData<List<Lessons>> getAllLessons();

    @Query("Select * From Lessons  Where lesson_name like '%' || :lessonName || '%'")
    LiveData<List<Lessons>> searchByLessonName(String lessonName);

    @Query("SELECT * FROM Lessons WHERE lesson_id = :lessonId")
    Lessons getLessonById(int lessonId);

    @Query("SELECT * FROM Lessons WHERE course_id = :courseId")
    LiveData<List<Lessons>> getLessonsByCourseId(int courseId);
}
