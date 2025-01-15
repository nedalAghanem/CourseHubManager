package com.example.coursehubmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.database.entity.Enrollments;
import com.example.coursehubmanager.databinding.ActivityShowCourseBinding;

import java.util.Date;

public class ShowCourseActivity extends AppCompatActivity {
    ActivityShowCourseBinding binding ;
    private CourseHubViewModel viewModel;
    int courseId ;
    int userId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        userId = i.getIntExtra("user_id", -1);
        courseId = i.getIntExtra("course_id", -1);

        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);
        Courses course = viewModel.getCourseById(courseId);
        if (course != null) {
            //            binding.showCourseIv.setImageResource(course.getImage_url());
            binding.showCourseTvCategory.setText(course.getCategory());
            binding.showCourseTvName.setText(course.getCourse_name());
            binding.showCourseTvDescription.setText(course.getDescription());
            binding.showCourseTvInstructorName.setText(course.getInstructor_name());
            binding.showCourseTvPrice.setText(course.getPrice() + " $ ");
            binding.showCourseTvTotalHoure.setText(course.getTotal_houre() + " /h ");
            binding.showCourseTvCourseDate.setText(course.getCourse_date() + "");
            binding.showCourseTvLessonCount.setText(course.getLesson_count() + "");
            binding.showCourseTvRegisteredUser.setText(course.getRegistered_user() + "");
        }

        binding.showCourseBtnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        binding.showCourseBtnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (course != null) {
                    Enrollments enrollment = new Enrollments(userId,courseId,new Date());
                    new Thread(() -> {
                        viewModel.insertEnrollment(enrollment);
                        runOnUiThread(() -> Toast.makeText(getBaseContext(), "Course Enrolled Successfully!", Toast.LENGTH_SHORT).show());
                    }).start();
                }
            }
        });

    }
}