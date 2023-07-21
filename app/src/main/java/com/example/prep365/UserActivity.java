package com.example.prep365;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private EditText weightField;
    private EditText heightField;
    private TextView bmiField;
    private Button calculateBMIButton;
    private Button addAllergyButton;
    private Button removeAllergyButton;
    private Button viewAllergiesButton;
    private Button mealPlanButton;

    private Profile profile;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        weightField = findViewById(R.id.weightInput);
        heightField = findViewById(R.id.heightInput);
        calculateBMIButton = findViewById(R.id.calculateBMIbutton);
        bmiField = findViewById(R.id.calculatedBMI);

         calculateBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w, h;
                if ((w = String.valueOf(weightField.getText())).isEmpty() || (h = String.valueOf(heightField.getText())).isEmpty()) {
                    Toast.makeText(UserActivity.this, "Weight or height field empty/invalid", Toast.LENGTH_SHORT).show();
                } else {
                    profile = new Profile(Double.parseDouble(w), Double.parseDouble(h));
                    bmiField.setText("Placeholder");
                }
            }
        });
    }
}
