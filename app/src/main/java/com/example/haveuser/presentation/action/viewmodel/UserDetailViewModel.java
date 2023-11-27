package com.example.haveuser.presentation.action.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.haveuser.data.repository.UserRepository;
import com.example.haveuser.domain.entities.UserEntity;

public class UserDetailViewModel extends AndroidViewModel {

    public MutableLiveData<UserEntity> findedUser = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> isDeletedUser = new MutableLiveData<>(null);

    private UserRepository userRepository;
    public UserDetailViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public void getUser(int userId){
        AsyncTask.execute(() -> {
            try {
                UserEntity user = userRepository.getUserById(userId);
                findedUser.postValue(user);
            } catch (RuntimeException exception) {
                Log.i("test", exception.getMessage());
            }
        });
    }

    public void deleteUserById(int userId){
        AsyncTask.execute(() -> {
            try {
                userRepository.deleteUserById(userId);
                isDeletedUser.postValue(true);
            } catch (RuntimeException exception) {
                Log.i("test", exception.getMessage());
            }
        });
    }
}
