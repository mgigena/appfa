package com.appfa.android.firebase;

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

        void onLoginFailed(); // TODO añadir tipo de error
    }
}
