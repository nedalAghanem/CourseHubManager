package com.example.coursehubmanager.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.coursehubmanager.database.DateConverter;

import java.util.Date;

@Entity
@TypeConverters({DateConverter.class})
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int course_id;
    @NonNull
    private String course_name ;
    @NonNull
    private String description ;
    private String instructor_name ;
    @NonNull
    private String image_url ;
    @NonNull
    private double price ;
    private int registered_user ;
    private double total_houre ;
    private int lesson_count ;
    private Date course_date ;

    public Courses(int course_id, @NonNull String course_name, @NonNull String description, String instructor_name, @NonNull String image_url, double price, int registered_user, double total_houre, int lesson_count, Date course_date) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.description = description;
        this.instructor_name = instructor_name;
        this.image_url = image_url;
        this.price = price;
        this.registered_user = registered_user;
        this.total_houre = total_houre;
        this.lesson_count = lesson_count;
        this.course_date = course_date;
    }

    public Courses(@NonNull String course_name, @NonNull String description, String instructor_name, @NonNull String image_url, double price, int registered_user, double total_houre, int lesson_count, Date course_date) {
        this.course_name = course_name;
        this.description = description;
        this.instructor_name = instructor_name;
        this.image_url = image_url;
        this.price = price;
        this.registered_user = registered_user;
        this.total_houre = total_houre;
        this.lesson_count = lesson_count;
        this.course_date = course_date;
    }

    public Courses(@NonNull String course_name, String instructor_name, @NonNull String image_url, double price, double total_houre, Date course_date) {
        this.course_name = course_name;
        this.instructor_name = instructor_name;
        this.image_url = image_url;
        this.price = price;
        this.total_houre = total_houre;
        this.course_date = course_date;
    }

    public int getCourse_id() {
        return course_id;
    }

    @NonNull
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(@NonNull String course_name) {
        this.course_name = course_name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    @NonNull
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(@NonNull String image_url) {
        this.image_url = image_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRegistered_user() {
        return registered_user;
    }

    public void setRegistered_user(int registered_user) {
        this.registered_user = registered_user;
    }

    public double getTotal_houre() {
        return total_houre;
    }

    public void setTotal_houre(double total_houre) {
        this.total_houre = total_houre;
    }

    public int getLesson_count() {
        return lesson_count;
    }

    public void setLesson_count(int lesson_count) {
        this.lesson_count = lesson_count;
    }

    public Date getCourse_date() {
        return course_date;
    }

    public void setCourse_date(Date course_date) {
        this.course_date = course_date;
    }
}
