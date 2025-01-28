package com.example.coursehubmanager.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Lessons;
import com.example.coursehubmanager.databinding.ActivityLessonsBinding;
import com.example.coursehubmanager.databinding.ActivityLoginBinding;
import com.example.coursehubmanager.ui.adapters.LessonAdapter;
import com.example.coursehubmanager.ui.interfaces.OnLessonClickListener;

import java.util.ArrayList;
import java.util.List;

public class LessonsActivity extends AppCompatActivity {
    ActivityLessonsBinding binding ;
    LessonAdapter adapter;
    List<Lessons> lll;
    int courseId ;
    private CourseHubViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLessonsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseId = getIntent().getIntExtra("course_id", 0);

        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);
        viewModel.getLessonByCourseId(courseId).observe(this, lessons -> {
            if (lessons != null) {
                Log.d("Lessons", "Number of lessons: " + lessons.size());
                adapter = new LessonAdapter(lessons, new OnLessonClickListener() {
                    @Override
                    public void onLessonClick(String url) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube"); // للتأكد من فتح الرابط في تطبيق YouTube
                        try {
                            startActivity(intent);
                        } catch (
                                ActivityNotFoundException e) {
                            // في حال لم يكن تطبيق YouTube مثبت فتح الرابط في المتصفح
                            intent.setPackage(null);
                            startActivity(intent);
                    }
                    }
                });
                binding.lessonRv.setAdapter(adapter);
                binding.lessonRv.setLayoutManager(new LinearLayoutManager(this));
                binding.lessonRv.setHasFixedSize(true);
                adapter.notifyDataSetChanged();

            } else {
                Log.d("Lessons", "No lessons found for courseId: " + courseId);
            }
        });

        binding.lessonIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


