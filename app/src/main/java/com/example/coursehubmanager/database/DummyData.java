package com.example.coursehubmanager.database;

import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.database.entity.Lessons;

import java.util.ArrayList;
import java.util.Date;

public class DummyData {

    // to add fake Courses
    public static ArrayList<Courses> addCourses() {
        ArrayList<Courses> courses = new ArrayList<>();
        courses.add(new Courses("Android Programming", "Learn Android development with Java and XML", "John Doe", "android_image_url", 100.0, 20, 50, 30, new Date(),"Programming"));
        courses.add(new Courses("Photoshop Design", "Master the art of photo editing and design", "Jane Smith", "photoshop_image_url", 80.0, 15, 40, 25, new Date(),"Design"));
        courses.add(new Courses("English Language Basics", "Improve your English speaking and writing skills", "Emily Johnson", "english_image_url", 50.0, 30, 20, 15, new Date(),"Languages"));
        courses.add(new Courses("Web Development", "Learn HTML, CSS, and JavaScript", "Michael Brown", "webdev_image_url", 120.0, 25, 60, 40, new Date(),"Programming"));
        courses.add(new Courses("Digital Marketing", "Learn the basics of SEO and social media marketing", "Sophia Wilson", "marketing_image_url", 90.0, 18, 30, 20, new Date(),"Design"));
        courses.add(new Courses("Python Programming", "Learn Python from basics to advanced topics", "Chris Taylor", "python_image_url", 110.0, 22, 50, 35, new Date(),"Programming"));
        courses.add(new Courses("Food Science", "Introduction to data analysis and machine learning", "Laura Davis", "datascience_image_url", 150.0, 12, 70, 50, new Date(),"Food"));

        return courses;
    }

    // to add fake Lessons for Courses
    public static ArrayList<Lessons> addLessonsForCourse(int courseId) {
        ArrayList<Lessons> lessons = new ArrayList<>();

        switch (courseId) {
            case 0:
                lessons.add(new Lessons(courseId, "Introduction to Android", "https://www.youtube.com/watch?v=android_intro"));
                lessons.add(new Lessons(courseId, "Layouts in Android", "https://www.youtube.com/watch?v=android_layouts"));
                lessons.add(new Lessons(courseId, "Working with Activities", "https://www.youtube.com/watch?v=android_activities"));
                lessons.add(new Lessons(courseId, "Intermediate Topics", "https://www.youtube.com/watch?v=intermediate_video"));
                lessons.add(new Lessons(courseId, "Advanced Topics", "https://www.youtube.com/watch?v=advanced_video"));
                lessons.add(new Lessons(courseId, "Conclusion", "https://www.youtube.com/watch?v=conclusion_video"));

                break;

            case 1:
                lessons.add(new Lessons(courseId, "Getting Started with Photoshop", "https://www.youtube.com/watch?v=photoshop_start"));
                lessons.add(new Lessons(courseId, "Editing Basics", "https://www.youtube.com/watch?v=photoshop_editing"));
                lessons.add(new Lessons(courseId, "Intermediate Topics", "https://www.youtube.com/watch?v=intermediate_video"));
                lessons.add(new Lessons(courseId, "Advanced Topics", "https://www.youtube.com/watch?v=advanced_video"));
                lessons.add(new Lessons(courseId, "Conclusion", "https://www.youtube.com/watch?v=conclusion_video"));

                break;

            case 2:
                lessons.add(new Lessons(courseId, "Grammar Basics", "https://www.youtube.com/watch?v=english_grammar"));
                lessons.add(new Lessons(courseId, "Improving Vocabulary", "https://www.youtube.com/watch?v=english_vocabulary"));
                lessons.add(new Lessons(courseId, "Intermediate Topics", "https://www.youtube.com/watch?v=intermediate_video"));
                lessons.add(new Lessons(courseId, "Advanced Topics", "https://www.youtube.com/watch?v=advanced_video"));
                lessons.add(new Lessons(courseId, "Conclusion", "https://www.youtube.com/watch?v=conclusion_video"));

                break;

            case 3:
                lessons.add(new Lessons(courseId, "Introduction to HTML", "https://www.youtube.com/watch?v=html_intro"));
                lessons.add(new Lessons(courseId, "CSS Basics", "https://www.youtube.com/watch?v=css_basics"));
                lessons.add(new Lessons(courseId, "JavaScript Essentials", "https://www.youtube.com/watch?v=javascript_essentials"));
                break;

            case 4:
                lessons.add(new Lessons(courseId, "SEO Basics", "https://www.youtube.com/watch?v=seo_basics"));
                lessons.add(new Lessons(courseId, "Social Media Marketing", "https://www.youtube.com/watch?v=smm_basics"));
                break;

            case 5:
                lessons.add(new Lessons(courseId, "Introduction to Python", "https://www.youtube.com/watch?v=python_intro"));
                lessons.add(new Lessons(courseId, "Working with Functions", "https://www.youtube.com/watch?v=python_functions"));
                lessons.add(new Lessons(courseId, "Data Structures in Python", "https://www.youtube.com/watch?v=python_datastructures"));
                break;

            case 6:
                lessons.add(new Lessons(courseId, "Introduction to Data Science", "https://www.youtube.com/watch?v=datascience_intro"));
                lessons.add(new Lessons(courseId, "Machine Learning Basics", "https://www.youtube.com/watch?v=ml_basics"));
                break;

            default:
                lessons.add(new Lessons(courseId, "Default Lesson", "https://www.youtube.com/watch?v=default_lesson"));
                break;
        }

        return lessons;
    }


}
