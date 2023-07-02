package com.example.maprunnersar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maprunnersar.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText inputEmail;
    private EditText inputPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();

        // Get the fields from view
        inputEmail = findViewById(R.id.inputEmail);
        inputPwd = findViewById(R.id.inputPwd);
    }

    @Override
    public void onStart() {
        super.onStart();
        // For now the user is always not logged in, this just stays here as a reference
        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //do something because the user is logged in
        }*/
    }

    public void onLoginClick(View view) {
        String email = inputEmail.getText().toString();
        String pwd = inputPwd.getText().toString();

        mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Log.d(LoginActivity.this.toString(), "signInWithEmail:success");
                clearInputFields();
                // Change to the Main Activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }else{
                Log.w(LoginActivity.this.toString(), "signInWithEmail:failure", task.getException());
                //Warn the user the login was invalid
                Toast.makeText(LoginActivity.this, "Login failed.",
                        Toast.LENGTH_SHORT).show();
                clearInputFields();
            }
        });
    }

    public void changeViewToSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        clearInputFields();
    }

    private void clearInputFields() {
        inputEmail.getText().clear();
        inputPwd.getText().clear();
    }
}