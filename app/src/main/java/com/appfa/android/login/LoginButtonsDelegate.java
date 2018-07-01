package com.appfa.android.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class LoginButtonsDelegate {

    private final Button loginButton;
    private final Button registerButton;

    private boolean isUserEmpty = true;
    private boolean isPasswordEmpty = true;

    public LoginButtonsDelegate(Button loginButton, Button registerButton) {
        this.loginButton = loginButton;
        this.registerButton = registerButton;
    }

    public void onUserFieldTextChanged(@NonNull final String text) {
        isUserEmpty = TextUtils.isEmpty(text);
        showButtons();
    }

    public void onPasswordFieldTextChanged(@NonNull final String text) {
        isPasswordEmpty = TextUtils.isEmpty(text);
        showButtons();
    }

    private void showButtons() {
        if (isUserEmpty && isPasswordEmpty) {
            loginButton.setVisibility(View.GONE);
            registerButton.setVisibility(View.VISIBLE);
        } else {
            registerButton.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
        }
    }
}
