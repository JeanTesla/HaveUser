package com.example.haveuser.presentation.action.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.haveuser.data.repository.UserRepository;
import com.example.haveuser.domain.entities.UserEntity;
import com.example.haveuser.domain.enums.TipoPessoaEnum;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel extends AndroidViewModel {
    public MutableLiveData<List<UserEntity>> userList = new MutableLiveData<>(null);

    private final UserRepository userRepository;

    public UserListViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public void getUsers() {
        AsyncTask.execute(() -> {
            try {
                List<UserEntity> userEntityList = userRepository.getAll();
                userList.postValue(userEntityList);
            } catch (RuntimeException exception) {
                Log.i("test", exception.getMessage());
            }
        });
    }

    public void getUsersLikeName(String name){
        AsyncTask.execute(() -> {
            try {
                List<UserEntity> userEntityList = userRepository.getUsersLikeName(name);
                userList.postValue(userEntityList);
            } catch (RuntimeException exception) {
                Log.i("test", exception.getMessage());
            }
        });
    }
}
