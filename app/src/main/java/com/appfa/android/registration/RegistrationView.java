package com.appfa.android.registration;

import com.appfa.android.base.view.BaseView;

public interface RegistrationView  extends BaseView {

    void onRegistrationSuccessful();
    void onRegistrationFailed();// TODO añadir tipo de error
}
