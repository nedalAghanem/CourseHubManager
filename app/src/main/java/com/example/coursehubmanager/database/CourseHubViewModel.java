package com.example.coursehubmanager.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.coursehubmanager.database.dao.EnrollmentsDao;
import com.example.coursehubmanager.database.entity.Bookmarks;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.database.entity.Enrollments;
import com.example.coursehubmanager.database.entity.Lessons;
import com.example.coursehubmanager.database.entity.Progress;
import com.example.coursehubmanager.database.entity.Users;

import java.util.List;

public class CourseHubViewModel extends AndroidViewModel {
    CourseHubRepository courseHubRepository;

    public CourseHubViewModel(@NonNull Application application) {
        super(application);
        courseHubRepository = new CourseHubRepository(application);
    }
    /////*** UsersDao ***/////
    public void insertUser(Users user) {
        courseHubRepository.insertUser(user);
    }

    public void deleteUsers(Users user) {
        courseHubRepository.deleteUsers(user);
    }


    public LiveData<List<Users>> getAllUsers(){
        return courseHubRepository.getAllUsers();
    }

    public Users loginUser(String email, String password){
        return courseHubRepository.loginUser(email, password);
    }

    public int returnIdByEmail(String email){
        return courseHubRepository.returnIdByEmail(email);
    }

    public Users returnUserByEmail(String email){
        return courseHubRepository.returnUserByEmail(email);
    }

    /////*** CoursesDao ***/////

    public void insertCourses(Courses... course){
        courseHubRepository.insertCourses(course);
    }

    public void updateCourses(Courses course){
        courseHubRepository.updateCourses(course);
    }

    public void deleteCourses(Courses course){
        courseHubRepository.deleteCourses(course);
    }

    public Courses getCourseById(int courseId){
        return courseHubRepository.getCourseById(courseId);
    }

    public LiveData<List<Courses>> getAllCourses(){
        return courseHubRepository.getAllCourses();
    }

    public LiveData<List<Courses>> searchByCourseName(String courseName){
        return courseHubRepository.searchByCourseName(courseName);
    }
    /////*** LessonsDao ***/////

    public void insertLesson(Lessons lesson){
        courseHubRepository.insertLesson(lesson);
    }

    public void updateLesson(Lessons lesson){
        courseHubRepository.updateLesson(lesson);
    }

    public void deleteLesson(Lessons lesson){
        courseHubRepository.deleteLesson(lesson);
    }

    public LiveData<List<Lessons>> getAllLessons(){
        return courseHubRepository.getAllLessons();
    }

    public LiveData<List<Lessons>> searchByLessonName(String lessonName){
        return courseHubRepository.searchByLessonName(lessonName);
    }

    public Lessons getLessonById(int lessonId){
        return courseHubRepository.getLessonById(lessonId);
    }

    /////*** BookmarksDao ***/////

    public void insertBookmark(Bookmarks bookmark){
        courseHubRepository.insertBookmark(bookmark);
    }

    public void deleteBookmark(Bookmarks bookmark){
        courseHubRepository.deleteBookmark(bookmark);
    }

    public LiveData<List<Bookmarks>> getAllBookmark(){
        return courseHubRepository.getAllBookmark();
    }

    public Bookmarks getBookmarkById(int bookmarkId){
        return courseHubRepository.getBookmarkById(bookmarkId);
    }

    /////*** EnrollmentsDao ***/////

    public void insertEnrollment(Enrollments enrollment){
        courseHubRepository.insertEnrollment(enrollment);
    }

    public void deleteEnrollment(Enrollments enrollment){
        courseHubRepository.deleteEnrollment(enrollment);
    }

    public LiveData<List<Enrollments>> getAllEnrollments(){
        return courseHubRepository.getAllEnrollments();
    }

    public Enrollments getEnrollmentById(int enrollmentId){
        return courseHubRepository.getEnrollmentById(enrollmentId);
    }

    /////*** ProgressDao ***/////

    public void insertProgress(Progress progress){
        courseHubRepository.insertProgress(progress);
    }

    public Progress getProgressById(int progresId){
        return courseHubRepository.getProgressById(progresId);
    }
}
