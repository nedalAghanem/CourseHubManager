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
public class Progress {
    @PrimaryKey(autoGenerate = true)
    private int progres_id ;
    @NonNull
    private int user_id ;
    @NonNull
    private int course_id ;
    private int completed_lessons ;
    private int total_lessons ;

    public Progress(int progres_id, int user_id, int course_id, int completed_lessons, int total_lessons) {
        this.progres_id = progres_id;
        this.user_id = user_id;
        this.course_id = course_id;
        this.completed_lessons = completed_lessons;
        this.total_lessons = total_lessons;
    }

    public Progress(int user_id, int course_id, int completed_lessons, int total_lessons) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.completed_lessons = completed_lessons;
        this.total_lessons = total_lessons;
    }

    public int getProgres_id() {
        return progres_id;
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

    public int getCompleted_lessons() {
        return completed_lessons;
    }

    public void setCompleted_lessons(int completed_lessons) {
        this.completed_lessons = completed_lessons;
    }

    public int getTotal_lessons() {
        return total_lessons;
    }

    public void setTotal_lessons(int total_lessons) {
        this.total_lessons = total_lessons;
    }
}
