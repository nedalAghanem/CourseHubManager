package com.example.coursehubmanager.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.DummyData;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.databinding.CourseContentItemBinding;

import java.util.List;


public class CourseCategoryFragment extends Fragment {
    CourseHubViewModel viewModel ;

    private static final String ARG_CATEGORY = "category";

    public CourseCategoryFragment() {
        // Required empty public constructor
    }

    public static CourseCategoryFragment newInstance(String category) {
        CourseCategoryFragment fragment = new CourseCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_category, container, false);

        RecyclerView rv = view.findViewById(R.id.fragment_course_category_rv);

        String category = getArguments().getString(ARG_CATEGORY);
        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);
        // قم بتحميل الكورسات بناءً على التصنيف
        viewModel.getCoursesByCategory(category).observe(getViewLifecycleOwner(),
                new Observer<List<Courses>>() {
                    @Override
                    public void onChanged(List<Courses> courses) {
                        // عند تحديث البيانات يقوم بتحديث الكود
                        CourseAdapter adapter = new CourseAdapter(courses, getContext());
                        rv.setAdapter(adapter);
                        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
                        rv.setHasFixedSize(true);
                        adapter.notifyDataSetChanged();
                    }
                });
        return view;
    }

    class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
        List<Courses> coursesList;
        Context context;
        public CourseAdapter(List<Courses> coursesList, Context context) {
            this.coursesList = coursesList;
            this.context = context;
            DummyData.addCourses();
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CourseViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.course_content_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            Courses courses = coursesList.get(position);
            holder.bind(courses);
        }

        @Override
        public int getItemCount() {
            return coursesList.size();
        }

        class CourseViewHolder extends RecyclerView.ViewHolder{
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
                binding.courseItemTvDate.setText(courses.getCourse_date()+"");
                binding.courseItemTvPrice.setText(courses.getPrice() + " $ ");
                // to show just 40 Char from the description
                String fullText = courses.getDescription();
                int numberOfCharacters = 40;
                String shortenedText = fullText.substring(0, Math.min(numberOfCharacters, fullText.length()));
                binding.courseItemTvDescription.setText(shortenedText);
            }
        }
    }

}