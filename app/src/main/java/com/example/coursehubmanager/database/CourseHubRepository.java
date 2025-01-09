package com.example.coursehubmanager.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.example.coursehubmanager.database.dao.BookmarksDao;
import com.example.coursehubmanager.database.dao.CoursesDao;
import com.example.coursehubmanager.database.dao.EnrollmentsDao;
import com.example.coursehubmanager.database.dao.LessonsDao;
import com.example.coursehubmanager.database.dao.ProgressDao;
import com.example.coursehubmanager.database.dao.UsersDao;
import com.example.coursehubmanager.database.entity.Bookmarks;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.database.entity.Enrollments;
import com.example.coursehubmanager.database.entity.Lessons;
import com.example.coursehubmanager.database.entity.Progress;
import com.example.coursehubmanager.database.entity.Users;

import java.util.List;

public class CourseHubRepository {
    UsersDao usersDao;
    CoursesDao coursesDao;
    LessonsDao lessonsDao;
    BookmarksDao bookmarksDao;
    EnrollmentsDao enrollmentsDao;
    ProgressDao progressDao;

    public CourseHubRepository(Application application) {
        CourseHubManagerDatabase db = CourseHubManagerDatabase.getDatabase(application);
        usersDao = db.usersDao();
        coursesDao = db.coursesDao();
        lessonsDao = db.lessonsDao();
        bookmarksDao = db.bookmarksDao();
        enrollmentsDao = db.enrollmentsDao();
        progressDao = db.progressDao();
    }

    /////*** UsersDao ***/////
//    public void insertUser(Users user) {
//        usersDao.insertUser(user);
//    }
    public void insertUser(Users user) {
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                usersDao.insertUser(user); // لتنفيذ الكود في الوركر ثريد
            }
        });
    }

    public void deleteUsers(Users user) {
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                usersDao.deleteUsers(user);
            }
        });

    }


    public LiveData<List<Users>> getAllUsers(){
        return usersDao.getAllUsers();
    }

    public Users loginUser(String email, String password){
        return usersDao.loginUser(email, password);
    }

    public int returnIdByEmail(String email){
        return usersDao.returnIdByEmail(email);
    }

    public Users returnUserByEmail(String email){
        return usersDao.returnUserByEmail(email);
    }

    /////*** CoursesDao ***/////

    public void insertCourses(Courses... course){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                coursesDao.insertCourses(course);
            }
        });
    }

    public void updateCourses(Courses course){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                coursesDao.updateCourses(course);
            }
        });
    }

    public void deleteCourses(Courses course){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                coursesDao.deleteCourses(course);
            }
        });
    }

    public Courses getCourseById(int courseId){
        return coursesDao.getCourseById(courseId);
    }

    public LiveData<List<Courses>> getAllCourses(){
        return coursesDao.getAllCourses();
    }

    public LiveData<List<Courses>> searchByCourseName(String courseName){
        return coursesDao.searchByCourseName(courseName);
    }
    public LiveData<List<Courses>> getCoursesByCategory(String category){
        return coursesDao.getCoursesByCategory(category);
    }
    public LiveData<List<String>> getCategories(){
        return coursesDao.getCategories();
    }

    /////*** LessonsDao ***/////

    public void insertLesson(Lessons lesson){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                lessonsDao.insertLesson(lesson);
            }
        });
    }

    public void updateLesson(Lessons lesson){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                lessonsDao.updateLesson(lesson);
            }
        });
    }

    public void deleteLesson(Lessons lesson){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                lessonsDao.deleteLesson(lesson);
            }
        });
    }

    public LiveData<List<Lessons>> getAllLessons(){
        return lessonsDao.getAllLessons();
    }

    public LiveData<List<Lessons>> searchByLessonName(String lessonName){
        return lessonsDao.searchByLessonName(lessonName);
    }

    public Lessons getLessonById(int lessonId){
        return lessonsDao.getLessonById(lessonId);
    }

    /////*** BookmarksDao ***/////

    public void insertBookmark(Bookmarks bookmark){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookmarksDao.insertBookmark(bookmark);
            }
        });
    }

    public void deleteBookmark(Bookmarks bookmark){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookmarksDao.deleteBookmark(bookmark);
            }
        });
    }

    public LiveData<List<Bookmarks>> getAllBookmark(){
        return bookmarksDao.getAllBookmark();
    }

    public Bookmarks getBookmarkById(int bookmarkId){
        return bookmarksDao.getBookmarkById(bookmarkId);
    }

    /////*** EnrollmentsDao ***/////

    public void insertEnrollment(Enrollments enrollment){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                enrollmentsDao.insertEnrollment(enrollment);
            }
        });
    }

    public void deleteEnrollment(Enrollments enrollment){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                enrollmentsDao.deleteEnrollment(enrollment);
            }
        });
    }

    public LiveData<List<Enrollments>> getAllEnrollments(){
        return enrollmentsDao.getAllEnrollments();
    }

    public Enrollments getEnrollmentById(int enrollmentId){
        return enrollmentsDao.getEnrollmentById(enrollmentId);
    }

    /////*** ProgressDao ***/////
    
    public void insertProgress(Progress progress){
        CourseHubManagerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                progressDao.insertProgress(progress);
            }
        });
    }

    public Progress getProgressById(int progresId){
        return progressDao.getProgressById(progresId);
    }


}
