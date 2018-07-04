package com.appfa.android.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;

import butterknife.BindView;

public class LoginActivity extends AppFaBaseActivity<LoginView, LoginPresenter> implements LoginView, LoginViewDelegate.LoginDelegateCallback {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPassword)
    EditText userPassword;
    @BindView(R.id.showPassword)
    CheckBox showPassword;
    @BindView(R.id.registerUser)
    Button registerButton;
    @BindView(R.id.loginUser)
    Button loginButton;

    private LoginViewDelegate buttonsDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
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
    public void isUserLogged(boolean isLogged) {
        if (isLogged) {

        } else {
            buttonsDelegate = new LoginViewDelegate(loginButton, registerButton, userName, userPassword, showPassword, this);
        }
    }

    @Override
    public void onLoginSuccessful() {

    }

    @Override
    public void onLoginFailed() {

    }
}
