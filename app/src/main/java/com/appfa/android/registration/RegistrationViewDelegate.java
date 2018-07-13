package com.appfa.android.registration;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appfa.android.R;
import com.appfa.android.annotation.ErrorMessages;
import com.appfa.android.base.BaseViewDelegate;
import com.appfa.android.model.dto.UserDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appfa.android.annotation.ErrorMessages.DATA_REQUIRED;
import static com.appfa.android.annotation.ErrorMessages.PASSWORDS_DO_NOT_MATCH;

public class RegistrationViewDelegate extends BaseViewDelegate {
    @BindView(R.id.userFirstName)
    EditText userFirstName;
    @BindView(R.id.userLastName)
    EditText userLastName;
    @BindView(R.id.userID)
    EditText userID;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPassword)
    EditText userPassword;
    @BindView(R.id.userRepeatPassword)
    EditText userRepeatPassword;
    @BindView(R.id.registerUser)
    Button register;
    @BindView(R.id.registerErrorMessage)
    TextView registerErrorMessage;

    private final RegistrationDelegateCallback callback;

    public RegistrationViewDelegate(View view, RegistrationDelegateCallback callback) {
        super(callback.getActivity());
        ButterKnife.bind(this, view);
        this.callback = callback;
        setBehavior();
    }

    private void setBehavior() {
        setUpButtonsActions();
    }

    private void setUpButtonsActions() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardUtils.hideKeyboard();
                if (isDataCompleted()) {
                    if (checkIfPasswordsMatch()) {
                        if (callback != null) {
                            callback.onRegisterButtonPressed(getUser());
                        }
                    } else {
                        showErrorMessage(PASSWORDS_DO_NOT_MATCH);
                    }
                } else {
                    showErrorMessage(DATA_REQUIRED);
                }
            }
        });
    }

    private boolean isDataCompleted() {
        return
                !userFirstName.getText().toString().isEmpty() &&
                        !userLastName.getText().toString().isEmpty() &&
                        !userName.getText().toString().isEmpty() &&
                        !userID.getText().toString().isEmpty() &&
                        !userPassword.getText().toString().isEmpty() &&
                        !userRepeatPassword.getText().toString().isEmpty();

    }

    private boolean checkIfPasswordsMatch() {
        String password = userPassword.getText().toString();
        String repeatedPassword = userRepeatPassword.getText().toString();

        return password.equals(repeatedPassword);
    }

    private UserDTO getUser() {
        String fullName = userFirstName.getText().toString();
        String lastName = userLastName.getText().toString();
        String email = userName.getText().toString();
        String id = userID.getText().toString();
        String password = userPassword.getText().toString();

        return new UserDTO(fullName, lastName, email, id, password);
    }

    protected void showErrorMessage(@ErrorMessages int errorMessage) {
        registerErrorMessage.setText(errorMessage);
    }

    interface RegistrationDelegateCallback extends BaseDelegateCallback {

        void onRegisterButtonPressed(@NonNull UserDTO userDTO);
    }
}
