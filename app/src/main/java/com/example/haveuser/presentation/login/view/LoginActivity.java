package com.example.haveuser.presentation.login.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.haveuser.R;
import com.example.haveuser.presentation.action.view.ActionActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.button_login);
        button.setOnClickListener(item -> {
            Intent intent = new Intent(this, ActionActivity.class);
            startActivity(intent);
            finish();
        });
    }
}