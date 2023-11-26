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

    @Query("SELECT iif(COUNT(user_name) > 0, true, false) as result " +
            "FROM userentity where user_name = :userName")
    boolean userNameAlreadyExists(String userName);

    @Query("SELECT * from userentity where uid = :userId")
    UserEntity getUserById(int userId);

    @Insert
    void insert(UserEntity user);


}
