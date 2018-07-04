package com.appfa.android.firebase;

public interface FirebaseLogin {

    interface ChechUserCallback {
        void isUserLogged(boolean isLogged);
    }

    interface AttempLoginCallback {
        void onLoginSuccessful();

        void onLoginFailed(); // TODO añadir tipo de error
    }
}
