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
import com.example.coursehubmanager.databinding.ActivityRegisterBinding;
import com.example.coursehubmanager.ui.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding ;
    CourseHubViewModel viewModel;
    String userFirstName;
    String userLastName;
    String userEmail;
    String userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        viewModel = new CourseHubViewModel(getApplication());


        binding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userFirstName = binding.registerEtFirstname.getText().toString().trim();
                userLastName = binding.registerEtLastname.getText().toString().trim();
                userEmail = binding.registerEtEmail.getText().toString().trim();
                userPassword = binding.registerEtPassword.getText().toString().trim();


                if (userFirstName.equals("")) {
                    binding.registerEtFirstname.setError("Please Add the FirstName");
                    userFirstName = null;
                }
                if (userLastName.equals("")) {
                    binding.registerEtLastname.setError("Please Add the LastName");
                    userLastName = null;
                }
                if (!userEmail.contains("@")) {
                    binding.registerEtEmail.setError("Email must contain '@'");
                    userEmail = null;
                }
                if (userPassword.equals("")) {
                    binding.registerEtPassword.setError("Please Add the Password");
                    userPassword = null;
                }
                if (userFirstName != null && userLastName != null && userEmail != null && userPassword != null) {

                    Users loggedInUserBuEmail = viewModel.returnUserByEmail(userEmail);
                    if (loggedInUserBuEmail != null) {
                        Toast.makeText(RegisterActivity.this, "You Already have account", Toast.LENGTH_SHORT).show();
                    } else {
                        viewModel.insertUser(new Users(userFirstName,userLastName, userEmail, userPassword));
                        Toast.makeText(RegisterActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        int id = viewModel.returnIdByEmail(userEmail);
                        intent.putExtra("userId", id);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }

                binding.registerBtnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });



    }
}