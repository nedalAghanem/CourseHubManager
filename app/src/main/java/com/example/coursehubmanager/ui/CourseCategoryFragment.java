package com.example.coursehubmanager.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.databinding.CourseContentItemBinding;
import com.example.coursehubmanager.ui.adapters.CourseAdapter;
import com.example.coursehubmanager.ui.interfaces.OnCourseClickListener;

import java.util.List;


public class CourseCategoryFragment extends Fragment {

    private static final String ARG_CATEGORY = "category";
    private static final String ARG_USER_ID = "user_id";
    private RecyclerView recyclerView;
    CourseAdapter adapter ;
    private CourseHubViewModel viewModel;

    public static CourseCategoryFragment newInstance(String category,int userId) {
        CourseCategoryFragment fragment = new CourseCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        args.putInt(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_category, container, false);

        recyclerView = view.findViewById(R.id.fragment_course_category_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);
        String category = getArguments().getString(ARG_CATEGORY, "all");
        int userId = getArguments().getInt(ARG_USER_ID,-1);

        viewModel.getCoursesByCategory(category).observe(getViewLifecycleOwner(), courses -> {
            if (adapter == null) {
            if (courses != null) {
                adapter = new CourseAdapter(courses, new OnCourseClickListener() {
                    @Override
                    public void onCourseClick(int userId, Courses course) {
                        Intent intent = new Intent(getContext(), ShowCourseActivity.class);
                        intent.putExtra("user_id", userId);
                        intent.putExtra("course_id", course.getCourse_id());
                        startActivity(intent);
                    }
                });
                adapter.setUserId(userId);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                adapter.notifyDataSetChanged();
            } else {
                Log.d("GetCourses", "No Courses: ");
            }
            } else {
                adapter.updateCourses(courses);
            }
        });

        return view;
    }
    }