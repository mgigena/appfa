package com.appfa.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;
import com.appfa.android.registration.RegistrationActivity;

public class LoginActivity extends AppFaBaseActivity<LoginView, LoginPresenter> implements LoginView, LoginViewDelegate.LoginDelegateCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @NonNull
    @Override
    public LoginView getMvpView() {
        return this;
    }

    @Override
    public void onLoginButtonPressed(@NonNull String userName, @NonNull String password) {
        getPresenter().attempLogin(userName, password);
    }

    @Override
    public void onRegistrationSelected() {
        goToRegistration();
    }

    @Override
    public void isUserLogged(boolean isLogged) {
        if (isLogged) {

        } else {
            new LoginViewDelegate(getWindow().getDecorView().getRootView(), this, this);
        }
    }

    @Override
    public void onLoginSuccessful() {

    }

    @Override
    public void onLoginFailed() {

    }

    private void goToRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
