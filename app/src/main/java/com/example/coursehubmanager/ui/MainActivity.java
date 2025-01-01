package com.example.coursehubmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursehubmanager.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        int userId = intent.getIntExtra("userId",-1);
//        Toast.makeText(this, "Id : "+userId, Toast.LENGTH_SHORT).show();

    }
}