package com.example.coursehubmanager.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Users.class, Courses.class, Lessons.class, Bookmarks.class, Enrollments.class, Progress.class},
        version = 7, exportSchema = false)
public abstract class CourseHubManagerDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();

    public abstract CoursesDao coursesDao();

    public abstract LessonsDao lessonsDao();

    public abstract BookmarksDao bookmarksDao();

    public abstract EnrollmentsDao enrollmentsDao();

    public abstract ProgressDao progressDao();

    private static volatile CourseHubManagerDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CourseHubManagerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CourseHubManagerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CourseHubManagerDatabase.class, "course_hub_Manager_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {

                CourseHubManagerDatabase dbInstance = INSTANCE;
                if (dbInstance != null) {
                    CoursesDao coursesDao = dbInstance.coursesDao();
                    LessonsDao lessonsDao = dbInstance.lessonsDao();

                    // Add A Courses
                    ArrayList<Courses> courses = DummyData.addCourses();
                    long[] courseIds = coursesDao.insertFakeCourses(courses);

                    // Add A Lessons for each course
                    for (int i = 0; i < courseIds.length; i++) {
                        ArrayList<Lessons> sampleLessons = DummyData.addLessonsForCourse((int) courseIds[i]);
                        lessonsDao.insertFakeLesson(sampleLessons);
                    }
                }
//                if (dbInstance != null) {
//                    CoursesDao coursesDao = dbInstance.coursesDao();
//                    LessonsDao lessonsDao = dbInstance.lessonsDao();
//                    // Add A Courses
//                    ArrayList<Courses> courses = DummyData.addCourses();
//                    coursesDao.insertFakeCourses(courses);
//                    // Add A Lessons for each course
//                    for (int i = 0; i < courses.size(); i++) {
//                        ArrayList<Lessons> sampleLessons = DummyData.addLessonsForCourse(courses.get(i).getCourse_id());
//                        lessonsDao.insertFakeLesson(sampleLessons);
//                    }
//                }
            });
        }
    };
}
