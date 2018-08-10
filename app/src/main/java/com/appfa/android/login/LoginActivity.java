package com.appfa.android.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.appfa.android.R;
import com.appfa.android.annotation.ErrorMessages;
import com.appfa.android.base.AppFaBaseActivity;
import com.appfa.android.custom.ui.dialog.CustomDialog;
import com.appfa.android.main.MainActivity;
import com.appfa.android.registration.RegistrationActivity;

public class LoginActivity extends AppFaBaseActivity<LoginView, LoginPresenter> implements LoginView, LoginViewDelegate.LoginDelegateCallback {

    private CustomDialog.Builder progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new LoginViewDelegate(getWindow().getDecorView().getRootView(), this);
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
    public Activity getActivity() {
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
    public void showLoading() {
        progressDialog = new CustomDialog.Builder(this);
        progressDialog.setTitle(R.string.loadingTitle);
        progressDialog.showLoading();
    }

    @Override
    public void hideLoading() {
        progressDialog.cancel();
    }

    @Override
    public void isUserLogged(boolean isLogged) {
        if (isLogged) {
            goToMain();
        }
    }

    @Override
    public void onLoginSuccessful() {
        goToMain();
    }

    @Override
    public void onLoginFailed(@ErrorMessages int errorCode) {
        new CustomDialog.Builder(this)
                .setImage(R.mipmap.ic_error_dialog).setTitle(errorCode).show();
    }

    private void goToRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
