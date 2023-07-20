package com.example.prep365;

public enum UserRegistrationResults {
    SUCCESS,
    EMPTY_FIELDS,
    INVALID_USERNAME,
    SHORT_PASSWORD,
    PASSWORDS_DO_NOT_MATCH,
    USERNAME_TAKEN,

    ERROR_SAVING_CREDENTIALS,
}
