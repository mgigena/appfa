package com.appfa.android.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;
import com.appfa.android.base.BasePresenter;
import com.appfa.android.base.view.BaseView;

import butterknife.BindView;

public class LoginActivity extends AppFaBaseActivity implements LoginView{

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

        buttonsDelegate = new LoginViewDelegate(loginButton, registerButton, userName, userPassword, showPassword);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @NonNull
    @Override
    public BaseView getMvpView() {
        return this;
    }
}
