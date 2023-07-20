package com.example.prep365;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private EditText passwordField2;
    private ImageButton joinButton;
    private ImageButton noAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        joinButton = findViewById(R.id.joinSignUp);
        usernameField = findViewById(R.id.usernameFieldSignUp);
        passwordField = findViewById(R.id.passwordFieldSignUp);
        passwordField2 = findViewById(R.id.passwordFieldSignUp2);
        noAccountButton = findViewById(R.id.noAccountButton);

        joinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        noAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    private void registerUser() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirmPassword = passwordField2.getText().toString().trim();

        UserManager manager = UserManager.createNewManager(this);
        UserRegistrationResults registrationResult = manager.registerUser(username, password, confirmPassword);

        switch (registrationResult) {
            case ERROR_SAVING_CREDENTIALS:
                Toast.makeText(this, "Registration not succesful.", Toast.LENGTH_SHORT).show();
                break;
            case SUCCESS:
                Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show();
                openMainActivity();
                break;
            case EMPTY_FIELDS:
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                break;
            case INVALID_USERNAME:
                Toast.makeText(this, "Invalid username format. Username must be capped at 12 characters and can only contain letters, numbers, and underscores.", Toast.LENGTH_SHORT).show();
                break;
            case SHORT_PASSWORD:
                Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
                break;
            case PASSWORDS_DO_NOT_MATCH:
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                break;
            case USERNAME_TAKEN:
                Toast.makeText(this, "Username is already taken.", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}