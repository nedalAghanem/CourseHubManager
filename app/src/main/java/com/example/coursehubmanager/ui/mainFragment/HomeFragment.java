package com.example.coursehubmanager.ui.mainFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.ui.CourseCategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private CoursePagerAdapter pagerAdapter;
    private CourseHubViewModel viewModel;

    private static final String ARG_USER_ID = "user_id";
    private int userId;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static AccountFragment newInstance(int userId) {
        AccountFragment fragment = new AccountFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toast.makeText(getContext(), "user id "+userId, Toast.LENGTH_SHORT).show();

        tabLayout = view.findViewById(R.id.fragment_home_tab_view);
        viewPager = view.findViewById(R.id.fragment_home_view_pager);
        viewModel = new ViewModelProvider(this).get(CourseHubViewModel.class);

        viewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            List<String> tabs = new ArrayList<>();
            tabs.add("all"); // إضافة التبويب الثابت "الكل"
            tabs.addAll(categories);

            pagerAdapter = new CoursePagerAdapter(this, tabs);
            viewPager.setAdapter(pagerAdapter);

            TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs.get(position));
            }
        });
            mediator.attach();

        });

        return view;
    }


    public class CoursePagerAdapter extends FragmentStateAdapter {

        private final List<String> categories;

        public CoursePagerAdapter(@NonNull Fragment fragment, List<String> categories) {
            super(fragment);
            this.categories = categories;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return CourseCategoryFragment.newInstance(categories.get(position));
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
    }
}