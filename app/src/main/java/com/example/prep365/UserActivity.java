package com.example.prep365;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class UserActivity extends AppCompatActivity {

    Profile profile;
    String current_user;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.current_user = getIntent().getStringExtra("current_user");

        EditText weightField = findViewById(R.id.weightInput);
        EditText heightField = findViewById(R.id.heightInput);
        Button calculateBMIButton = findViewById(R.id.calculateBMIbutton);
        TextView bmiField = findViewById(R.id.calculatedBMI);
        TextView bmiClassField = findViewById(R.id.bmiClass);
        Button addAllergyButton = findViewById(R.id.addAllergyButton);
        Button removeAllergyButton = findViewById(R.id.removeAllergyButton);
        Button viewAllergiesButton = findViewById(R.id.viewAllergiesButton);
        profile = new Profile();
        profile.loadUserAllergies(current_user, this);

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

        //allergy filter buttons
        addAllergyButton.setOnClickListener(view -> createAndShowAddDialog());
        removeAllergyButton.setOnClickListener(view -> createAndShowRemoveDialog());
        viewAllergiesButton.setOnClickListener(view -> displayAllergies());
    }

    private void createAndShowAddDialog() {
        AlertDialog.Builder builder = buildAllergyInputDialog();

        EditText inputField = new EditText(this);
        inputField.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(inputField);

        AlertDialog dialog = builder.create();

        setupAddAllergyButtonActions(dialog, inputField);
        dialog.show();
    }

    private void createAndShowRemoveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Allergy");

        String[] allergies = new String[profile.allergyList.size()];
        allergies = profile.allergyList.toArray(allergies);

        boolean[] checkedItems = new boolean[allergies.length];
        builder.setMultiChoiceItems(allergies, checkedItems, (dialog, which, isChecked) ->
                checkedItems[which] = isChecked
        );

        String[] finalAllergies = allergies; // android studio throws warnings without this
        builder.setPositiveButton("Remove", (dialog, which) -> {
            for (int i = 0; i < checkedItems.length; i++) {
                if (checkedItems[i]) {
                    profile.allergyList.remove(finalAllergies[i]);
                }
            }
            profile.updateUserAllergies(current_user);
            Toast.makeText(getApplicationContext(), "Allergies Removed", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private AlertDialog.Builder buildAllergyInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Allergy");
        builder.setPositiveButton("Add", null);
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        return builder;
    }

    private void setupAddAllergyButtonActions(AlertDialog dialog, EditText inputField) {
        dialog.setOnShowListener(dialogInterface -> {
            Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(alertView -> {
                String allergy = inputField.getText().toString();

                if (allergy.isEmpty()) {
                    Toast.makeText(this, "Input cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    profile.allergyList.add(allergy);
                    profile.updateUserAllergies(current_user);
                    dialog.dismiss();
                }
            });
        });
    }

    public void displayAllergies() {
        StringBuilder allergiesList = new StringBuilder();

        profile.allergyList.forEach(s -> allergiesList.append(s).append('\n'));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Allergies");
        builder.setMessage(allergiesList.toString());
        builder.setPositiveButton("OK", null);

        builder.create().show();
    }

}
