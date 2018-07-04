package com.appfa.android.login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.appfa.android.R;
import com.appfa.android.annotation.ErrorMessages;
import com.appfa.android.utils.KeyboardUtils;
import com.appfa.android.utils.TextBaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appfa.android.annotation.ErrorMessages.DATA_REQUIRED;

public class LoginViewDelegate {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPassword)
    EditText userPassword;
    @BindView(R.id.showPassword)
    CheckBox showPassword;
    @BindView(R.id.loginUser)
    Button loginButton;
    @BindView(R.id.loginErrorMessage)
    TextView loginErrorMessage;
    @BindView(R.id.registrationTextLink)
    TextView registrationLink;

    private boolean isUserEmpty = true;
    private boolean isPasswordEmpty = true;

    private final KeyboardUtils keyboardUtils;

    private final LoginDelegateCallback callback;

    public LoginViewDelegate(View view, LoginDelegateCallback callback, Activity activity) {
        ButterKnife.bind(this, view);
        this.callback = callback;

        keyboardUtils = new KeyboardUtils(activity);

        setBehavior();
    }

    private void setBehavior() {
        setUpShowPasswordCheckbox();
        setUpButtonsActions();
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
                keyboardUtils.hideKeyboard();
                if (isDataCompleted()) {
                    if (callback != null) {
                        final String user = userName.getText().toString();
                        final String password = userPassword.getText().toString();
                        callback.onLoginButtonPressed(TextBaseUtils.getFormattedUser(user), password);
                    }
                } else {
                    showErrorMessage(DATA_REQUIRED);
                }
            }
        });

        registrationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onRegistrationSelected();
                }
            }
        });
    }

    private boolean isDataCompleted() {
        return !userName.getText().toString().isEmpty() && !userPassword.getText().toString().isEmpty();
    }

    private void showErrorMessage(@ErrorMessages int errorMessage) {
        String messge = loginErrorMessage.getContext().getString(errorMessage);
        loginErrorMessage.setText(messge);
    }

    interface LoginDelegateCallback {
        void onLoginButtonPressed(@NonNull String userName, @NonNull String password);

        void onRegistrationSelected();
    }
}
