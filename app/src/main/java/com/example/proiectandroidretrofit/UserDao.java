package com.example.proiectandroidretrofit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

    public interface UserDao
    {
        @Insert
        public void addUser(User user);

        @Query("select * from users")
        public List<User> getAllUsers();
        @Query("select * from users where user_name LIKE :username")
        public User getAccount(String username);

        @Delete
        public void deleteUser(User user);

        @Update
        public void updateUser(User user);
    }
