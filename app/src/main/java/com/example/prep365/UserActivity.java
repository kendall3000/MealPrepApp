package com.example.prep365;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    Profile profile;

     @SuppressLint("DefaultLocale")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        EditText weightField = findViewById(R.id.weightInput);
        EditText heightField = findViewById(R.id.heightInput);
        Button calculateBMIButton = findViewById(R.id.calculateBMIbutton);
        TextView bmiField = findViewById(R.id.calculatedBMI);
        TextView bmiClassField = findViewById(R.id.bmiClass);
        profile = new Profile();

         calculateBMIButton.setOnClickListener(view -> {
             String w, h;
             if ((w = String.valueOf(weightField.getText())).isEmpty()
                     || (h = String.valueOf(heightField.getText())).isEmpty()
                     || Double.parseDouble(w) < 1) {
                 Toast.makeText(UserActivity.this, "Weight or height field empty/invalid", Toast.LENGTH_SHORT).show();
             } else {
                 profile.setWeight(Double.parseDouble((w)));
                 profile.setHeight(Double.parseDouble((h)));
                 profile.calculateBMI();
                 bmiField.setText(String.format("BMI: %.2f", profile.getBmi()));
                 bmiClassField.setText(String.format("BMI Range: %s", profile.bmiClass.toString()));
             }
         });
    }
}
