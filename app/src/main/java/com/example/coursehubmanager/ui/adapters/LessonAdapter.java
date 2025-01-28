package com.example.coursehubmanager.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.entity.Lessons;
import com.example.coursehubmanager.databinding.LessonContentItemBinding;
import com.example.coursehubmanager.ui.interfaces.OnLessonClickListener;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder>{

    private final List<Lessons> lessons;
    private int userId;
    private OnLessonClickListener listener;

    public LessonAdapter(List<Lessons> lessons, OnLessonClickListener listener) {
        this.lessons = lessons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_content_item,parent,false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lessons lesson = lessons.get(position);
        holder.bind(lesson);
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onLessonClick(lesson.getYoutube_url()));}
            }


    @Override
    public int getItemCount() {
        return lessons == null ? 0 : lessons.size();
    }


    public class LessonViewHolder extends RecyclerView.ViewHolder {
        LessonContentItemBinding binding;
        Lessons lesson ;
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LessonContentItemBinding.bind(itemView);
        }
        public void bind(Lessons lesson) {
            this.lesson = lesson;
            binding.lessonItemTvName.setText(lesson.getLesson_name());
            String fullText = lesson.getYoutube_url();
            int numberOfCharacters = 40;
            String shortenedText = fullText.substring(0, Math.min(numberOfCharacters, fullText.length()));
            binding.lessonItemTvYoutubeUrl.setText(shortenedText);
        }
    }
}

