package com.example.coursehubmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.databinding.ActivityMainBinding;
import com.example.coursehubmanager.ui.mainFragment.AccountFragment;
import com.example.coursehubmanager.ui.mainFragment.HomeFragment;
import com.example.coursehubmanager.ui.mainFragment.MyCoursesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    int userId;
    private FragmentManager fragmentManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",-1);
        Toast.makeText(this, "Id : "+userId, Toast.LENGTH_SHORT).show();

        fragmentManager = getSupportFragmentManager();
//        binding.homeBottomNav.setSelectedItemId(R.id.item_home);
        addFragment(new HomeFragment());

        binding.homeBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_home){
                    addFragment(HomeFragment.newInstance(userId));
                }else if (item.getItemId() == R.id.item_my_courses){
                    addFragment(new MyCoursesFragment());
                } else if (item.getItemId() == R.id.item_account) {
//                    addFragment(new AccountFragment());
                    addFragment(AccountFragment.newInstance(userId));
                }
                return false;
            }
        });

    }
    private void addFragment(Fragment fragment){
        fragmentManager.beginTransaction().add(R.id.home_container,fragment).commit();
    }
}