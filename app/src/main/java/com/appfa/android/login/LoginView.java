package com.appfa.android.login;

import com.appfa.android.base.view.BaseView;

public interface LoginView extends BaseView {

    void isUserLogged(boolean isLogged);

    void onLoginSuccessful();

    void onLoginFailed(); // TODO añadir tipo de error
}
