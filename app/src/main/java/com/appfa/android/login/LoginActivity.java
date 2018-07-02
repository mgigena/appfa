package com.appfa.android.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;

import butterknife.BindView;

public class LoginActivity extends AppFaBaseActivity {

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

    private LoginButtonsDelegate buttonsDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        buttonsDelegate = new LoginButtonsDelegate(loginButton, registerButton);
        setUpShowPasswordCheckbox();
        setUpLoginRegisterButtonsVisibility();
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

    private void setUpLoginRegisterButtonsVisibility() {
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonsDelegate.onUserFieldTextChanged(charSequence.toString());
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
                buttonsDelegate.onPasswordFieldTextChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // not used
            }
        });
    }
}
