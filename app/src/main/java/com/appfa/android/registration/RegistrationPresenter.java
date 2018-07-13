package com.appfa.android.registration;

import android.support.annotation.NonNull;

import com.appfa.android.base.BasePresenter;
import com.appfa.android.firebase.FirebaseLogin;
import com.appfa.android.firebase.FirebaseManager;
import com.appfa.android.model.dto.UserDTO;

public class RegistrationPresenter extends BasePresenter<RegistrationView>
        implements RegistrationPresenterContract {

    @Override
    public void registerUser(@NonNull UserDTO userDTO) {
        FirebaseLogin.RegisterUserCallback callback = new FirebaseLogin.RegisterUserCallback() {
            @Override
            public void onRegistrationSuccessful() {
                if (isViewAttached()) {

                }
            }

            @Override
            public void onRegistrationFailed() {
                if (isViewAttached()) {

                }
            }
        };
        FirebaseManager.getInstance().registerUser(userDTO, callback);
    }
}
