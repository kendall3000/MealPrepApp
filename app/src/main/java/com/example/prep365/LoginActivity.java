package com.example.prep365;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private ImageButton continueButton;

    private ImageButton signInButton;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.usernameFieldLogIn);
        passwordField = findViewById(R.id.passwordFieldLogIn);
        continueButton = findViewById(R.id.continueButton);
        signInButton = findViewById(R.id.signInButton);

        continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                verifyInfo();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

    }
    private void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void verifyInfo() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (isEmpty(username) || isEmpty(password)) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else {
            UserManager manager = UserManager.createNewManager(this);

            boolean verified = manager.verifyCredentials(username, password);
            if (verified) {
                openUserActivity();
            } else {
                Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isEmpty(String info) {
        return TextUtils.isEmpty(info);
    }

    private void openUserActivity() {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }


}