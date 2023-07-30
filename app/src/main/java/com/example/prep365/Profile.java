package com.example.prep365;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class Profile {
    double weight;
    double height;
    double bmi;
    BMIClassification bmiClass;
    ArrayList<String> allergyList;
    SharedPreferences sharedPrefs;
    public Profile() {
        allergyList = new ArrayList<>();
    }

    public Profile(double weight, double height) {
        this.weight = weight;
        this.height = height;
        allergyList = new ArrayList<>();
        calculateBMI();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void calculateBMI() {
        this.setBmi((this.weight / Math.pow(this.height, 2)) * 10000);
        double roundedBMI = getBmi();
        if (roundedBMI < 16) {
            this.bmiClass = BMIClassification.SevereThinness;
        } else if (roundedBMI >= 16 && roundedBMI < 17) {
            this.bmiClass = BMIClassification.ModerateThinness;

        } else if (roundedBMI >= 17 && roundedBMI < 18.5) {
            this.bmiClass = BMIClassification.MildThinness;

        } else if (roundedBMI >= 18.5 && roundedBMI < 25) {
            this.bmiClass = BMIClassification.Normal;
        } else if (roundedBMI >= 25 && roundedBMI < 30) {
            this.bmiClass = BMIClassification.Overweight;
        } else if (roundedBMI >= 30 && roundedBMI < 35) {
            this.bmiClass = BMIClassification.ObeseClassI;
        } else if (roundedBMI >= 35 && roundedBMI < 40) {
            this.bmiClass = BMIClassification.ObeseClassII;
        } else {
            this.bmiClass = BMIClassification.ObeseClassIII;
        }
    }

    public void loadUserAllergies(String username, Context c) {
        this.sharedPrefs = c.getSharedPreferences("AllergyData", Context.MODE_PRIVATE);
        String allergyString = sharedPrefs.getString(username, "");
        if (!allergyString.equals(""))
            this.allergyList = new ArrayList<>(Arrays.asList(allergyString.split(",")));
    }

    public void updateUserAllergies(String current_user) {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        editor.putString(current_user, String.join(",", this.allergyList));
        editor.apply(); // async commit
    }

    enum BMIClassification {
        SevereThinness,
        ModerateThinness,
        MildThinness,
        Normal,
        Overweight,
        ObeseClassI,
        ObeseClassII,
        ObeseClassIII,
    }
}
