package com.example.haveuser.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.haveuser.domain.entities.UserEntity;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM userentity")
    List<UserEntity> getAll();

    @Insert
    void insertAll(UserEntity... users);
}
