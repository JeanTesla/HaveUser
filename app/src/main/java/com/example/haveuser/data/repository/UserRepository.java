package com.example.haveuser.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.haveuser.data.config.AppDatabase;
import com.example.haveuser.data.dao.UserDAO;
import com.example.haveuser.domain.entities.UserEntity;

import java.util.List;

public class UserRepository {

    private UserDAO userDAO;

    public UserRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        userDAO = appDatabase.userDAO();
    }

    public List<UserEntity> getAll(){
        return userDAO.getAll();
    }

    public UserEntity getUserById(int userId) { return userDAO.getUserById(userId);}

    public void persistUser(UserEntity user){
        userDAO.insert(user);
    }

    public boolean userAlreadyExists(String userName){
        return userDAO.userNameAlreadyExists(userName);
    }
}
