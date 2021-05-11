package com.example.proiectandroidretrofit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DepozitDBDao {

    @Insert
    public void addDepozit(DepozitDB depozitDB);

    @Query("select * from depozite where user_id =:u_id ")
    public List<DepozitDB> getAllDepozit(int u_id);

    @Delete
    public void deleteDepozit(DepozitDB depozitDB);

    @Update
    public void updateDepozit(DepozitDB depozitDB);
}
