package com.example.prep365;

import java.util.ArrayList;

public class Profile {

    double weight;
    double height;
    double bmi;
    ArrayList<String> allergyList;
    public Profile() {
    }

    public Profile(double weight, double height) {
        this.weight = weight;
        this.height = height;
        this.calculateBMI();
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
        //TODO
    }

    public ArrayList<String> getAllergyList() {
        return allergyList;
    }

    public void setAllergyList(ArrayList<String> allergyList) {
        this.allergyList = allergyList;
    }
}
