package com.example.coursehubmanager.ui;

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

import java.util.List;


public class CourseCategoryFragment extends Fragment {

    private static final String ARG_CATEGORY = "category";
    private RecyclerView recyclerView;
    private CourseHubViewModel viewModel;

    public static CourseCategoryFragment newInstance(String category) {
        CourseCategoryFragment fragment = new CourseCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
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

        viewModel.getCoursesByCategory(category).observe(getViewLifecycleOwner(), courses -> {
            if (courses != null) {
                CourseAdapter adapter = new CourseAdapter(courses);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                adapter.notifyDataSetChanged();
            } else {
                Log.d("GetCourses", "No Courses: ");
            }
        });

        return view;
    }


    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

        private final List<Courses> courses;
        private int userId;
        private OnCourseClickListener listener;

        public CourseAdapter(List<Courses> courses) {
            this.courses = courses;
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_content_item, parent, false);
            return new CourseViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            Courses course = courses.get(position);
            Log.d("GetCourses", courses.size() + "");
            holder.bind(course);
            holder.itemView.setOnClickListener(v -> listener.onCourseClick(userId,course));        }

        @Override
        public int getItemCount() {
            return courses == null ? 0 : courses.size();
        }

        public void updateCourses(List<Courses> newCourses) {
            this.courses.clear();
            this.courses.addAll(newCourses);
            notifyDataSetChanged();
        }

        class CourseViewHolder extends RecyclerView.ViewHolder {
            CourseContentItemBinding binding;
            Courses courses;

            public CourseViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = CourseContentItemBinding.bind(itemView);
            }

            public void bind(Courses courses) {
                this.courses = courses;
                binding.imageView.setImageResource(R.drawable.web_developer);
                binding.courseItemTvCategory.setText(courses.getCategory());
                binding.courseItemTvCourseName.setText(courses.getCourse_name());
                binding.courseItemTvInstructorName.setText(courses.getInstructor_name());
                binding.courseItemTvDate.setText(courses.getCourse_date() + "");
                binding.courseItemTvPrice.setText(courses.getPrice() + " $ ");
                // to show just 40 Char from the description
                String fullText = courses.getDescription();
                int numberOfCharacters = 40;
                String shortenedText = fullText.substring(0, Math.min(numberOfCharacters, fullText.length()));
                binding.courseItemTvDescription.setText(shortenedText);
            }
        }
    }
    public interface OnCourseClickListener {
        void onCourseClick(int userId, Courses course);
    }


}