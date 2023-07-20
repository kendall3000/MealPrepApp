package com.example.prep365;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
public class UserManager {
    private static UserManager manager;

    private static final String PREF_NAME = "pref";
    private static final String USERNAME_SET_KEY = "username_set";
    private SharedPreferences sharedPreferences;
    private Set<String> usernamesSet;
    private UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        usernamesSet = sharedPreferences.getStringSet(USERNAME_SET_KEY, new HashSet<>());
    }

    public static UserManager createNewManager(Context context) {
        if (manager == null) {
            manager = new UserManager(context);
        }
        return manager;
    }

    public UserRegistrationResults registerUser(String username, String password, String confirmPassword) {
        if (isEmpty(username) || isEmpty(password) || isEmpty(confirmPassword)) {
            return UserRegistrationResults.EMPTY_FIELDS;
        } else if (username.length() > 12 || !isValidUsername(username)) {
            return UserRegistrationResults.INVALID_USERNAME;
        } else if (password.length() < 6) {
            return UserRegistrationResults.SHORT_PASSWORD;
        } else if (!password.equals(confirmPassword)) {
            return UserRegistrationResults.PASSWORDS_DO_NOT_MATCH;
        } else if (isUsernameTaken(username)) {
            return UserRegistrationResults.USERNAME_TAKEN;
        } else {
            // Register the new user

            boolean saveSuccesful = saveCredentials(username, password);

            return (saveSuccesful ? UserRegistrationResults.SUCCESS : UserRegistrationResults.ERROR_SAVING_CREDENTIALS);

        }
    }

    private boolean saveCredentials(String username, String password) {

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(username, password);
            usernamesSet.add(username);
            editor.putStringSet(USERNAME_SET_KEY, usernamesSet);

            return editor.commit(); // Save was successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Save failed
        }
    }
    public boolean verifyCredentials(String username, String password) {
        String storedPassword = sharedPreferences.getString(username, null);
        return storedPassword != null && storedPassword.equals(password);
    }

    private boolean isEmpty(String info) {
        return info == null || info.trim().isEmpty();
    }

    private boolean isUsernameTaken(String username) {
        // Retrieve the set of usernames from SharedPreferences
        Set<String> usernamesSet = sharedPreferences.getStringSet(USERNAME_SET_KEY, new HashSet<>());
        return usernamesSet.contains(username);
    }

    private boolean isValidUsername(String username) {
        String pattern = "^[a-zA-Z0-9_]{1,12}$";
        return username.matches(pattern);
    }
}

