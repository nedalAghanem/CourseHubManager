package com.example.coursehubmanager.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.databinding.CourseContentItemBinding;
import com.example.coursehubmanager.ui.interfaces.OnCourseClickListener;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final List<Courses> courses;
    private int userId;
    private OnCourseClickListener listener;

    public CourseAdapter(List<Courses> courses, OnCourseClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onCourseClick(userId, course));}
    }

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