package com.example.prep365;

import java.util.ArrayList;

public class Profile {
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

    double weight;
    double height;
    double bmi;
    BMIClassification bmiClass;
    ArrayList<String> allergyList;
    public Profile() {
    }

    public Profile(double weight, double height) {
        this.weight = weight;
        this.height = height;
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
        this.setBmi((this.weight / Math.pow(this.height, 2))*10000);
        double roundedBMI = getBmi();
        if (roundedBMI < 16)
        {
            this.bmiClass = BMIClassification.SevereThinness;
        }

        else if (roundedBMI >= 16 && roundedBMI < 17)
        {
            this.bmiClass = BMIClassification.ModerateThinness;

        }

        else if (roundedBMI >= 17 && roundedBMI < 18.5)
        {
            this.bmiClass = BMIClassification.MildThinness;

        }

        else if (roundedBMI >= 18.5 && roundedBMI < 25)
        {
            this.bmiClass = BMIClassification.Normal;
        }

        else if (roundedBMI >= 25 && roundedBMI < 30)
        {
            this.bmiClass = BMIClassification.Overweight;
        }

        else if (roundedBMI >= 30 && roundedBMI < 35)
        {
            this.bmiClass = BMIClassification.ObeseClassI;
        }

        else if (roundedBMI >= 35 && roundedBMI < 40)
        {
            this.bmiClass = BMIClassification.ObeseClassII;
        }
        else {
            this.bmiClass = BMIClassification.ObeseClassIII;
        }
    }

    public ArrayList<String> getAllergyList() {
        return allergyList;
    }

    public void setAllergyList(ArrayList<String> allergyList) {
        this.allergyList = allergyList;
    }
}
