package com.example.coursehubmanager.ui.mainFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Courses;
import com.example.coursehubmanager.databinding.CourseContentItemBinding;
import com.example.coursehubmanager.ui.CourseCategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    CourseHubViewModel viewModel;
    TabLayout tabLayout;
    ViewPager2 viewPager;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);

        // مراقبة جميع الكورسات
        viewModel.getAllCourses().observe(getViewLifecycleOwner(),
                courses -> {
                    CourseAdapter adapter = new CourseAdapter(courses, getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                });
///////////////////
//        tabLayout = view.findViewById(R.id.fragment_home_tab_view);
//        viewPager = view.findViewById(R.id.fragment_home_view_pager);

//        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);

//        List<String> categories = new ArrayList<>(); // تحميل التصنيفات من قاعدة البيانات
//        categories.add("All");
//        CourseCategoryAdapter adapter = new CourseCategoryAdapter(this, categories);
//        viewPager.setAdapter(adapter);
//
//        viewModel.getCategories().observe(getViewLifecycleOwner(), // كي لا تدمر اللايف في الدوران
//                new Observer<List<String>>() {
//                    @Override
//                    public void onChanged(List<String> courses) {
//                        // تحديث Adapter
////                    adapter.updateCategories(categories);
//                        if (categories != null) {
//                            setupTabLayoutAndViewPager(categories);
//                        }
//
//                    }
//                });
//////////////////

//        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(categories.get(position));
//            }
//        });
//        mediator.attach();
//        // مثال على تحديث التصنيفات عند تغيير البيانات:
//        fetchCategoriesFromDatabase(categories -> {
//            adapter.updateCategories(categories);
//            tabLayout.removeAllTabs();
//            for (String category : categories) {
//                tabLayout.addTab(tabLayout.newTab().setText(category));
//            }
//        });
        return view;
    }
///////////////////////
//    private void setupTabLayoutAndViewPager(List<String> categories) {
//        // إعداد Adapter
//        CourseCategoryAdapter adapter = new CourseCategoryAdapter(this, categories);
//        viewPager.setAdapter(adapter);
//
//        // ربط TabLayout مع ViewPager2
////        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
////            tab.setText(categories.get(position)); // تعيين اسم التصنيف لكل تاب
////        }).attach();
//        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(categories.get(position));
//            }
//        });
//        mediator.attach();
//    }
///////////////////////
//    private List<String> fetchCategoriesFromDatabase() {
//        // استبدل هذا الجزء بجلب التصنيفات من قاعدة البيانات
//        return Arrays.asList("Programming", "Design", "Business");
//    }
//    private void setupRecyclerView(RecyclerView rv, List<Courses> courses) {
//        CourseCategoryAdapter adapter = new CourseCategoryAdapter(courses, getContext());
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        rv.setHasFixedSize(true);
//        adapter.notifyDataSetChanged();
//    }

////////////////////////////////////
//    // الكود الرئيسي
//    public class CourseCategoryAdapter extends FragmentStateAdapter {
//
//        private List<String> categories;
//
//        public CourseCategoryAdapter(@NonNull Fragment fragment, List<String> categories) {
//            super(fragment);
//            this.categories = categories;
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            return CourseCategoryFragment.newInstance(categories.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return categories.size();
//        }
//
//        public void updateCategories(List<String> newCategories) {
//            this.categories = newCategories;
//            notifyDataSetChanged();
//        }
//    }
////////////////////////////////
    // كود تجربي
    class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
        private List<Courses> coursesList;
        private Context context;

        public CourseAdapter(List<Courses> coursesList, Context context) {
            this.coursesList = coursesList;
            this.context = context;
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.course_content_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            Courses course = coursesList.get(position);
            holder.bind(course);
        }

        @Override
        public int getItemCount() {
            return coursesList.size();
        }

        class CourseViewHolder extends RecyclerView.ViewHolder {
            CourseContentItemBinding binding;

            public CourseViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = CourseContentItemBinding.bind(itemView);
            }

            public void bind(Courses course) {
                binding.imageView.setImageResource(R.drawable.web_developer);
                binding.courseItemTvCategory.setText(course.getCategory());
                binding.courseItemTvCourseName.setText(course.getCourse_name());
                binding.courseItemTvInstructorName.setText(course.getInstructor_name());
                binding.courseItemTvDate.setText(course.getCourse_date().toString());
                binding.courseItemTvPrice.setText(course.getPrice() + " $");

                //  وصف مختصر
                String description = course.getDescription();
                binding.courseItemTvDescription.setText(description.length() > 40
                        ? description.substring(0, 40) + "..."
                        : description);
            }
        }
    }

}