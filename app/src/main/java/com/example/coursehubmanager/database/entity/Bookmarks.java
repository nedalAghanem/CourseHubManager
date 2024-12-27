package com.example.coursehubmanager.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Users.class,
        parentColumns = {"user_id"},
        childColumns = {"user_id"},
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Courses.class,
                parentColumns = {"course_id"},
                childColumns = {"course_id"},
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)})
public class Bookmarks {
    @PrimaryKey(autoGenerate = true)
    private int bookmark_id;
    @NonNull
    private int user_id;
    @NonNull
    private int course_id;

    public Bookmarks(int bookmark_id, int user_id, int course_id) {
        this.bookmark_id = bookmark_id;
        this.user_id = user_id;
        this.course_id = course_id;
    }

    public Bookmarks(int user_id, int course_id) {
        this.user_id = user_id;
        this.course_id = course_id;
    }

    public int getBookmark_id() {
        return bookmark_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
