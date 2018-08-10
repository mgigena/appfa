package com.appfa.android.firebase;

import com.appfa.android.annotation.ErrorMessages;

public interface FirebaseLogin {

    interface RegisterUserCallback {
        void onRegistrationSuccessful();

        void onRegistrationFailed();// TODO añadir tipo de error
    }

    interface CheckUserCallback {
        void isUserLogged(boolean isLogged);
    }

    interface AttempLoginCallback {
        void onLoginSuccessful();

        void onLoginFailed(@ErrorMessages int errorCode);
    }
}
