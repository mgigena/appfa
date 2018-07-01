package com.appfa.android.login;

import android.os.Bundle;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;

import butterknife.BindView;

public class LoginActivity extends AppFaBaseActivity {

    @BindView(R.id.userPassword)
    EditText userPassword;
    @BindView(R.id.showPassword)
    CheckBox showPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        setUpShowPasswordCheckbox();
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
}
