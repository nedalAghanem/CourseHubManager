package com.example.coursehubmanager.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding ;
    CourseHubViewModel viewModel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new CourseHubViewModel(getApplication());

        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = binding.loginEtEmail.getText().toString().trim();
                String userPassword = binding.loginEtPassword.getText().toString().trim();
                if (!userEmail.contains("@")) {
                    binding.loginEtEmail.setError("Add Right Email!");
                }

                if (userPassword.equals("")) {
                    binding.loginEtPassword.setError("Please Enter your Password!");
                }

                if (userEmail != null && userPassword != null && userEmail != "" && userPassword != "") {
                    Users loginUser = viewModel.loginUser(userEmail, userPassword);
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
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        binding.loginCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("isRemembered", isChecked);
                editor.apply();
            }
        });

        final boolean[] isPasswordVisible = {false};
        binding.loginTextInputLayoutPassword.setEndIconDrawable(R.drawable.ic_visibility_off);

        binding.loginTextInputLayoutPassword.setEndIconOnClickListener(v -> {
            if (isPasswordVisible[0]) {
                binding.loginEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                binding.loginTextInputLayoutPassword.setEndIconDrawable(R.drawable.ic_visibility_off);
            } else {
                binding.loginEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                binding.loginTextInputLayoutPassword.setEndIconDrawable(R.drawable.ic_visibility_on);
            }
            binding.loginEtPassword.setSelection(binding.loginEtPassword.getText().length());
            isPasswordVisible[0] = !isPasswordVisible[0];
        });


    }
}