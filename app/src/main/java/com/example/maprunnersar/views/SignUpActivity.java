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

import java.util.stream.IntStream;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText inputEmail;
    private EditText inputPwd;
    private EditText inputConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Get the Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();

        // Get the fields from view
        inputEmail = findViewById(R.id.inputEmail);
        inputPwd = findViewById(R.id.inputPwd);
        inputConfirmPwd = findViewById(R.id.inputConfirmPwd);
    }

    public void onSignUpClick(View view) {
        // Validate the email and password fields, and stop the sign up if they are invalid
        if (!validateFields()) return;
        firebaseSignUp();
    }

    private boolean validateFields() {
        //Check if email is in valid format
        boolean isValidEmail = inputEmail.getText().toString().matches("[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
        if (!isValidEmail) {
            inputEmail.setError("The email must be in a valid format. Example: bob@gmail.com");
            return false;
        }

        //Check if the password and confirm password fields match
        boolean doPasswordsMatch = inputPwd.getText().toString().equals(inputConfirmPwd.getText().toString());
        if (!doPasswordsMatch) {
            inputConfirmPwd.setError("This field has to match the password above.");
            return false;
        }

        //Check if the password is minimum strong (min. 6 chars, 1 lower, 1 upper, 1 number)
        boolean pwdHasMinSize = inputPwd.getText().length() >= 6;
        boolean pwdHasLower = inputPwd.getText().chars().anyMatch(Character::isLowerCase);
        boolean pwdHasUpper = inputPwd.getText().chars().anyMatch(Character::isUpperCase);
        boolean pwdHasNumber = inputPwd.getText().chars().anyMatch(Character::isDigit);
        boolean isValidPwd = pwdHasMinSize && pwdHasLower && pwdHasUpper && pwdHasNumber;
        if (!isValidPwd) {
            inputPwd.setError("The password needs to be at least 6 characters long, at least 1 lowercase, 1 uppercase, and 1 digit.");
            return false;
        }

        // If nothing triggered the invalid ifs, then it's valid
        return true;
    }

    private void firebaseSignUp() {
        String email = inputEmail.getText().toString();
        String pwd = inputPwd.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                finish();
                Toast.makeText(getApplicationContext(), "Sign Up complete! You can now Login to your account.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignUpActivity.this, "Sorry. Sign Up failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void changeViewToLogin(View view) {
        finish();
    }
}