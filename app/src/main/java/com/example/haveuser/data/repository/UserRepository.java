package com.example.haveuser.data.repository;

import android.app.Application;

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

    public List<UserEntity> getUsersLikeName(String name){
        return userDAO.getUsersLikeName(name);
    }

    public UserEntity getUserById(int userId) { return userDAO.getUserById(userId);}

    public void persistUser(UserEntity user){
        userDAO.insert(user);
    }

    public boolean isOwnerOfUserName(String userName, int userId){
        return userDAO.isOwnerOfUserName(userName, userId);
    }

    public void updateUser(UserEntity user){
        userDAO.updateUser(user);
    }

    public boolean userAlreadyExists(String userName){
        return userDAO.userNameAlreadyExists(userName);
    }

    public void deleteUserById(int userId){
        userDAO.deleteUserById(userId);
    }
}
