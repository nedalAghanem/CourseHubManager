package com.example.coursehubmanager.ui.mainFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.ui.CourseCategoryFragment;
import com.example.coursehubmanager.ui.MainActivity;
import com.example.coursehubmanager.ui.ShowCourseActivity;
import com.example.coursehubmanager.ui.adapters.CourseAdapter;
import com.example.coursehubmanager.ui.interfaces.OnCourseClickListener;

import java.util.List;

public class MyCoursesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private CourseHubViewModel viewModel;

    private static final String ARG_USER_ID = "user_id";
    private int userId;

    public MyCoursesFragment() {
        // Required empty public constructor
    }
    public static MyCoursesFragment newInstance(int userId) {
        MyCoursesFragment fragment = new MyCoursesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_USER_ID,-1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_courses, container, false);
        recyclerView = view.findViewById(R.id.fragment_my_courses_rv);
        TextView tv = view.findViewById(R.id.fragment_my_courses_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);

            viewModel.getEnrolledCourses(userId).observe(getViewLifecycleOwner(), courses -> {
                Toast.makeText(getActivity(), "Length of list= "+courses.size(), Toast.LENGTH_SHORT).show();
                adapter = new CourseAdapter(courses, new OnCourseClickListener() {
                    @Override
                    public void onCourseClick(int userId, Courses course) {
                        Toast.makeText(getActivity(), "name: "+course.getCourse_name(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra("course_id", course.getCourse_id());
                        Toast.makeText(getActivity(), "The course has been added to my courses!"+"/"+course.getCourse_name(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                adapter.notifyDataSetChanged();
            });


        return view;
    }

}
