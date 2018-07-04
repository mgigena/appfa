package com.appfa.android.login;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.appfa.android.utils.TextBaseUtils;

public class LoginViewDelegate {

    private final Button loginButton;
    private final Button registerButton;
    private final EditText userName;
    private final EditText userPassword;
    private final CheckBox showPassword;

    private boolean isUserEmpty = true;
    private boolean isPasswordEmpty = true;

    private final LoginDelegateCallback callback;

    public LoginViewDelegate(Button loginButton, Button registerButton, EditText userName,
                             EditText userPassword, CheckBox showPassword, LoginDelegateCallback callback) {
        this.loginButton = loginButton;
        this.registerButton = registerButton;
        this.userName = userName;
        this.userPassword = userPassword;
        this.showPassword = showPassword;
        this.callback = callback;

        setBehavior();
    }

    private void setBehavior() {
        setUpLoginRegisterButtonsVisibility();
        setUpShowPasswordCheckbox();
        setUpButtonsActions();
    }

    private void onUserFieldTextChanged(@NonNull final String text) {
        isUserEmpty = TextUtils.isEmpty(text);
        showButtons();
    }

    private void onPasswordFieldTextChanged(@NonNull final String text) {
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

    private void setUpLoginRegisterButtonsVisibility() {
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onUserFieldTextChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // not used
            }
        });

        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onPasswordFieldTextChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // not used
            }
        });
    }

    private void setUpShowPasswordCheckbox() {
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                final int cursor = userPassword.getSelectionStart();
                if (isChecked) {
                    userPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    userPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                userPassword.setSelection(cursor);
            }
        });
    }

    private void setUpButtonsActions() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO checkear si los campos estan completos
                final String user = userName.getText().toString();
                final String password = userPassword.getText().toString();
                if (callback != null) {
                    callback.onLoginButtonPressed(TextBaseUtils.getFormattedUser(user), password);
                }
            }
        });
    }

    interface LoginDelegateCallback {
        void onLoginButtonPressed(@NonNull String userName, @NonNull String password);
    }
}
