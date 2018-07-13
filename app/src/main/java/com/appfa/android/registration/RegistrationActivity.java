package com.appfa.android.registration;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;
import com.appfa.android.model.dto.UserDTO;

public class RegistrationActivity extends AppFaBaseActivity<RegistrationView, RegistrationPresenter> implements
        RegistrationView, RegistrationViewDelegate.RegistrationDelegateCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        new RegistrationViewDelegate(getWindow().getDecorView().getRootView(), this);
    }

    @Override
    public RegistrationPresenter createPresenter() {
        return new RegistrationPresenter();
    }

    @NonNull
    @Override
    public RegistrationView getMvpView() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRegisterButtonPressed(@NonNull UserDTO userDTO) {
        getPresenter().registerUser(userDTO);
    }

    @Override
    public void onRegistrationSuccessful() {
        Toast.makeText(this, "Exitoso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailed() {
        Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
    }
}
