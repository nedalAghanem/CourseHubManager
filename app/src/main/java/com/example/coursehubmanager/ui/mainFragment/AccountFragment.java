package com.example.coursehubmanager.ui.mainFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.coursehubmanager.R;
import com.example.coursehubmanager.database.CourseHubViewModel;
import com.example.coursehubmanager.database.entity.Users;
import com.google.android.material.textfield.TextInputEditText;

public class AccountFragment extends Fragment {

    CourseHubViewModel viewModel;

    private static final String ARG_USER_ID = "user_id";

    private int userId;

    public AccountFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        viewModel = new CourseHubViewModel(getActivity().getApplication());

        TextInputEditText et_firstName = view.findViewById(R.id.fragment_account_et_first_name);
        TextInputEditText et_lastName = view.findViewById(R.id.fragment_account_et_last_name);
        TextInputEditText et_password = view.findViewById(R.id.fragment_account_et_password);
        TextInputEditText et_re_password = view.findViewById(R.id.fragment_account_et_re_password);
        Button btn_edit = view.findViewById(R.id.fragment_account_btn_edit);

        String email = viewModel.getEmailByUserId(userId);
        Users user = viewModel.returnUserByEmail(email);

        // هان في تعديل لكن لتجنب NullPointerException
        et_firstName.setText("Nedal");
        et_lastName.setText("AbuGhanem");// user.getLast_name()
        et_password.setText("123456789");// user.getPassword()
        et_re_password.setText("123456789");// user.getPassword()

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_edit.setText("Save");
                et_firstName.setEnabled(true);
                et_lastName.setEnabled(true);
                et_password.setEnabled(true);
                et_re_password.setEnabled(true);

                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String firsName = et_firstName.getText().toString().trim();
                        String lastName = et_lastName.getText().toString().trim();
                        String password = et_password.getText().toString().trim();
                        String re_password = et_re_password.getText().toString().trim();
                        if (firsName.equals("")) {
                            et_firstName.setError("Please Add the FirstName");
                        }
                        if (lastName.equals("")) {
                            et_lastName.setError("Please Add the LastName");
                        }
                        if (password.equals("")) {
                            et_password.setError("Please Add the Password");
                        }
                        if (re_password.equals("")) {
                            et_re_password.setError("Please Add the rePassword");
                        }
                        if (!password.equals(re_password)){
                            et_password.setError("Please Add the Same Password");
                            et_re_password.setError("Please Add the Same Password");
                        }else {
                            btn_edit.setText("Edit");
                            et_firstName.setEnabled(false);
                            et_lastName.setEnabled(false);
                            et_password.setEnabled(false);
                            et_re_password.setEnabled(false);

                            Users user = new Users(firsName,lastName,email,password);
                            viewModel.updateUser(user);

                            et_firstName.setText(user.getFirst_name());
                            et_lastName.setText(user.getLast_name());
                            et_lastName.setText(user.getLast_name());
                            et_lastName.setText(user.getLast_name());


                        }

                    }
                });



            }
        });


        return view;
    }
}