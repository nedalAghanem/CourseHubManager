package com.example.coursehubmanager.ui.interfaces;

import com.example.coursehubmanager.database.entity.Courses;

public interface OnCourseClickListener {
    void onCourseClick(int userId, Courses course);
}