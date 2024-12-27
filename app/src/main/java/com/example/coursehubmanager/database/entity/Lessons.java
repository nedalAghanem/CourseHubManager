package com.example.coursehubmanager.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Courses.class,
        parentColumns = {"course_id"},
        childColumns = {"course_id"},
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)})
public class Lessons {

    @PrimaryKey(autoGenerate = true)
    private int lesson_id ;
    @NonNull
    private int course_id ;
    private String lesson_name ;
    private String youtube_url ;

    public Lessons(int lesson_id, int course_id, String lesson_name, String youtube_url) {
        this.lesson_id = lesson_id;
        this.course_id = course_id;
        this.lesson_name = lesson_name;
        this.youtube_url = youtube_url;
    }

    public Lessons(int course_id, String lesson_name, String youtube_url) {
        this.course_id = course_id;
        this.lesson_name = lesson_name;
        this.youtube_url = youtube_url;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }
}
