package com.example.coursehubmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursehubmanager.database.entity.Bookmarks;
import com.example.coursehubmanager.database.entity.Courses;

import java.util.List;

public interface BookmarksDao {

    @Insert
    void insertBookmark(Bookmarks bookmark);

    @Delete
    void deleteBookmark(Bookmarks bookmark);

    @Query("Select * From Bookmarks order by bookmark_id asc")
    LiveData<List<Bookmarks>> getAllBookmark();

    @Query("SELECT * FROM Bookmarks WHERE bookmark_id = :bookmarkId")
    Bookmarks getBookmarkById(int bookmarkId);


}

//@Query("SELECT Users.userName, Users.email AS userEmail, COUNT(*) AS correctAnswers " +
//        "FROM Results " +
//        "INNER JOIN Users ON Results.userId = Users.user_id " +
//        "WHERE Results.isCorrect = 'Correct' " +
//        "GROUP BY Users.user_id")