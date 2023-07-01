package com.example.maprunnersar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.maprunnersar.R;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail);
        inputPwd = findViewById(R.id.inputPwd);
    }

    public void changeViewToSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        inputEmail.getText().clear();
        inputPwd.getText().clear();
    }
}