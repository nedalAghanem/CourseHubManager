package com.example.coursehubmanager.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Users;
import com.example.coursehubmanager.databinding.ActivityLoginBinding;
import com.example.coursehubmanager.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding ;
    CourseHubViewModel viewModel ;
    String userEmail;
    String userPassword ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        viewModel = new CourseHubViewModel(getApplication());

        userEmail = binding.loginEtEmail.getText().toString().trim();
        userPassword = binding.loginEtPassword.getText().toString().trim();

        if (!userEmail.contains("@")) {
            binding.loginEtEmail.setError("Please Enter the your Email!");
            userEmail = null;
        }
        if (userPassword.equals("")) {
            binding.loginEtPassword.setError("Please Enter the your Password!");
            userPassword = null;
        }

        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userEmail != null && userPassword != null) {
                    Users loginUser = viewModel.loginUser(userEmail,userPassword);
                    if (loginUser != null) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        int id = viewModel.returnIdByEmail(userEmail);
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        intent.putExtra("userId", id);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);

            }
        });



    }
}