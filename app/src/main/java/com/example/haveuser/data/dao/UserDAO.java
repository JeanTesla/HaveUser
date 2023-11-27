package com.example.haveuser.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.haveuser.domain.entities.UserEntity;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM userentity")
    List<UserEntity> getAll();

    @Query("SELECT * FROM userentity where nome like :name || '%'")
    List<UserEntity> getUsersLikeName(String name);

    @Query("SELECT iif(COUNT(user_name) > 0, true, false) as result " +
            "FROM userentity where user_name = :userName")
    boolean userNameAlreadyExists(String userName);

    @Query("SELECT * from userentity where uid = :userId")
    UserEntity getUserById(int userId);

    @Insert
    void insert(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Query("select iif(user_name == :userName, true, false) from userentity where uid = :userId")
    boolean isOwnerOfUserName(String userName, int userId);

    @Query("DELETE from userentity where uid = :userId")
    void deleteUserById(int userId);


}
