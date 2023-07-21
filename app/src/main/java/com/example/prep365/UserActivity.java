package com.example.prep365;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private EditText weightField;
    private EditText heightField;
    private TextView bmiField;
    private Button addAllergyButton;

    private Profile profile;

     @SuppressLint("DefaultLocale")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        weightField = findViewById(R.id.weightInput);
        heightField = findViewById(R.id.heightInput);
        Button calculateBMIButton = findViewById(R.id.calculateBMIbutton);
        bmiField = findViewById(R.id.calculatedBMI);
        TextView bmiClassField = findViewById(R.id.bmiClass);

         calculateBMIButton.setOnClickListener(view -> {
             String w, h;
             if ((w = String.valueOf(weightField.getText())).isEmpty() || (h = String.valueOf(heightField.getText())).isEmpty()) {
                 Toast.makeText(UserActivity.this, "Weight or height field empty/invalid", Toast.LENGTH_SHORT).show();
             } else {
                 profile = new Profile(Double.parseDouble(w), Double.parseDouble(h));
                 bmiField.setText(String.format("BMI: %.2f", profile.getBmi()));
                 bmiClassField.setText(String.format("BMI Range: %s", profile.bmiClass.toString()));
             }
         });
    }
}
