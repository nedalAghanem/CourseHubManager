package com.example.coursehubmanager.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.coursehubmanager.database.DateConverter;

import java.util.Date;
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
@TypeConverters({DateConverter.class})

public class Enrollments {
    @PrimaryKey(autoGenerate = true)
    private int enrollment_id ;
    @NonNull
    private int user_id ;
    @NonNull
    private int course_id ;
    private Date enrollment_date ;

    public Enrollments(int enrollment_id, int user_id, int course_id, Date enrollment_date) {
        this.enrollment_id = enrollment_id;
        this.user_id = user_id;
        this.course_id = course_id;
        this.enrollment_date = enrollment_date;
    }

    public Enrollments(int user_id, int course_id, Date enrollment_date) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.enrollment_date = enrollment_date;
    }

    public int getEnrollment_id() {
        return enrollment_id;
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

    public Date getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }
}
