package com.example.haveuser.presentation.action.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.haveuser.domain.entities.UserEntity;
import com.example.haveuser.domain.enums.TipoPessoaEnum;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel extends ViewModel {
    private MutableLiveData<List<UserEntity>> users;

    public MutableLiveData<List<UserEntity>> getUsers() {
        if(users == null){
            users = new MutableLiveData<List<UserEntity>>();
        }
        loadUsers();
        return users;
    }

    private void loadUsers(){
        Log.i("teste","Chegou aqui");

        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(new UserEntity("Jean", "jean@gmail.com", TipoPessoaEnum.FISICA));
        userEntityList.add(new UserEntity("Marcela", "marceladias@gmail.com", TipoPessoaEnum.JURIDICA));
        userEntityList.add(new UserEntity("Jean", "jean@gmail.com", TipoPessoaEnum.FISICA));
        userEntityList.add(new UserEntity("Marcela", "marceladias@gmail.com", TipoPessoaEnum.JURIDICA));
        userEntityList.add(new UserEntity("Jean", "jean@gmail.com", TipoPessoaEnum.FISICA));
        userEntityList.add(new UserEntity("Marcela", "marceladias@gmail.com", TipoPessoaEnum.JURIDICA));
        userEntityList.add(new UserEntity("Jean", "jean@gmail.com", TipoPessoaEnum.FISICA));
        userEntityList.add(new UserEntity("Marcela", "marceladias@gmail.com", TipoPessoaEnum.JURIDICA));
        userEntityList.add(new UserEntity("Jean", "jean@gmail.com", TipoPessoaEnum.FISICA));
        userEntityList.add(new UserEntity("Marcela", "marceladias@gmail.com", TipoPessoaEnum.JURIDICA));
        userEntityList.add(new UserEntity("Jean", "jean@gmail.com", TipoPessoaEnum.FISICA));
        userEntityList.add(new UserEntity("Marcela", "marceladias@gmail.com", TipoPessoaEnum.JURIDICA));

        users.setValue(userEntityList);
    }
}
