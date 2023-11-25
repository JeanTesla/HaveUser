package com.example.haveuser.data.config;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.haveuser.data.dao.UserDAO;
import com.example.haveuser.domain.entities.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
}
