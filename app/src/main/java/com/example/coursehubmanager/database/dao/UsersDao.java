package com.example.coursehubmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursehubmanager.database.entity.Users;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert
    void insertUser(Users user);

    @Update
    void updateUser(Users user);

    @Delete
    void deleteUsers(Users user);

    @Query("select * from Users order by first_name asc")
    LiveData<List<Users>> getAllUsers();

    // للتحقق من تسجيل الدخول باستخدام البريد الإلكتروني وكلمة المرور
    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    Users loginUser(String email, String password);

    @Query("SELECT Users.user_id FROM Users WHERE Users.email = :email ")
    int returnIdByEmail(String email);

    @Query("SELECT * FROM Users WHERE Users.email = :email ")
    Users returnUserByEmail(String email);

    @Query("Select  Users.email From Users Where user_id = :userId")
    String getEmailByUserId(int userId);


}
