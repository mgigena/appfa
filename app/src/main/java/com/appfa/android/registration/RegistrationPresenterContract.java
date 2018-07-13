package com.appfa.android.registration;

import android.support.annotation.NonNull;

import com.appfa.android.base.view.BasePresenterView;
import com.appfa.android.model.dto.UserDTO;

public interface RegistrationPresenterContract  extends BasePresenterView {

    void registerUser(@NonNull final UserDTO userDTO);
}
