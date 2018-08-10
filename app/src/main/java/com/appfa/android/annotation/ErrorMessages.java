package com.appfa.android.annotation;

import com.appfa.android.R;

public @interface ErrorMessages {
    int DATA_REQUIRED = R.string.error_data_required;
    int PASSWORDS_DO_NOT_MATCH = R.string.error_passwords_do_not_match;

    //    Firebase Errors
    int WEAK_PASSWORD = R.string.error_firebase_weak_password;
    int INVALID_CREDENTIALS = R.string.error_firebase_invalid_credentials;
    int USER_ALREADY_EXITS = R.string.error_firebase_user_exits;
    int GENERIC_ERROR = R.string.error_firebase_generic_error;
}
