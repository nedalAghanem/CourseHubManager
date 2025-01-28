package com.example.coursehubmanager.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.databinding.ActivityMainBinding;
import com.example.coursehubmanager.ui.mainFragment.AccountFragment;
import com.example.coursehubmanager.ui.mainFragment.HomeFragment;
import com.example.coursehubmanager.ui.mainFragment.MyCoursesFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int userId;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);

        if (userId == -1) {
            SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
            userId = sharedPreferences.getInt("user_id", -1);
        }

        if (userId != -1) {
            saveUserIdToPreferences(userId);
        }
        fragmentManager = getSupportFragmentManager();
        addFragment(HomeFragment.newInstance(userId));

        binding.homeBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_home) {
                    addFragment(HomeFragment.newInstance(userId));
                } else if (item.getItemId() == R.id.item_my_courses) {
                    addFragment(MyCoursesFragment.newInstance(userId));
                } else if (item.getItemId() == R.id.item_account) {
                    addFragment(AccountFragment.newInstance(userId));
                }
                return false;
            }
        });
    }

    private void saveUserIdToPreferences(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", userId);
        editor.apply();
    }
    private void addFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.home_container,fragment).commit();
    }
}