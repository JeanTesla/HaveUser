package com.example.haveuser.presentation.action.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.haveuser.data.repository.UserRepository;
import com.example.haveuser.domain.entities.UserEntity;
import com.vicmikhailau.maskededittext.MaskedEditText;

public class AddEditUserViewModel extends AndroidViewModel {

    public MutableLiveData<UserEntity> findedUser = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> isPersisted = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> isUpdated = new MutableLiveData<>(null);

    private final UserRepository userRepository;

    public AddEditUserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public void persistUser(UserEntity user) {
        AsyncTask.execute(() -> {
            try {
                if (userNameAlreadyExists(user.getUserName())) {
                    isPersisted.postValue(false);
                    return;
                }
                userRepository.persistUser(user);
                isPersisted.postValue(true);
            } catch (RuntimeException exception) {
                Log.i("test", exception.getMessage());
            }
        });
    }

    public void updateUser(UserEntity user, int userId){
        AsyncTask.execute(() -> {
            try {

                if(userRepository.isOwnerOfUserName(user.getUserName(), userId)){
                    Log.i("test", "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    user.setUid(userId);
                    userRepository.updateUser(user);
                    isUpdated.postValue(true);
                }

            } catch (RuntimeException exception) {
                Log.i("test", exception.getMessage());
            }
        });
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

    public boolean userNameAlreadyExists(String userName) {
        return userRepository.userAlreadyExists(userName);
    }

    public boolean validateCpfAndCnpj(boolean typeUser, MaskedEditText editText) {

        String val = editText.getText().toString();

        if (val.isEmpty()) {
            editText.setError("Esse campo não pode ser vazio");
            return false;
        } else if (!val.matches("^(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2}|\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$")) {
            editText.setError(typeUser ? "CNPJ inválido" : "CPF inválido");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public boolean validatePublicPlace(EditText editText) {
        String val = editText.getText().toString();
        if (val.isEmpty()) {
            editText.setError("Esse campo não pode ser vazio");
            return false;
        }
        editText.setError(null);
        return true;
    }

    public boolean validateBirthDate(boolean typeUser, MaskedEditText editText) {

        if (typeUser) return true;

        String val = editText.getText().toString();

        if (val.isEmpty()) {
            editText.setError("Esse campo não pode ser vazio");
            return false;
        } else if (!val.matches("\\d{2}/\\d{2}/\\d{4}")) {
            editText.setError("Data de nascimento inválida");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public boolean validateName(EditText editText) {
        String val = editText.getText().toString();
        if (val.isEmpty() || val.length() < 30) {
            editText.setError("Esse campo precisa ter mais de 30 caracteres");
            return false;
        }
        editText.setError(null);
        return true;
    }

    public boolean validateEmail(EditText editText) {
        String val = editText.getText().toString();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (val.isEmpty()) {
            editText.setError("Esse campo não pode ser vazio");
            return false;
        } else if (!val.matches(checkEmail)) {
            editText.setError("E-mail inválido");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public boolean validatePassword(EditText editText) {
        String val = editText.getText().toString();
        String checkPassword = "^.*(?=.{8,})(?=.*\\d)(?=.*[A-Z]).*$";
        if (val.isEmpty()) {
            editText.setError("Esse campo não pode ser vazio");
            return false;
        } else if (!val.matches(checkPassword)) {
            editText.setError("A senha deve possuir pelo menos 8 caracteres e ter uma letra maiúscula e um número");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public boolean validateUserName(EditText editText) {

        String val = editText.getText().toString();
        if (val.isEmpty()) {
            editText.setError("Esse campo não pode ser vazio");
            return false;
        } else if (val.length() > 20) {
            editText.setError("Nome de usuário muito longo");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }
}
