package com.appfa.android.login;

import com.appfa.android.annotation.ErrorMessages;
import com.appfa.android.base.view.BaseView;

public interface LoginView extends BaseView {

    void showLoading();

    void hideLoading();

    void isUserLogged(boolean isLogged);

    void onLoginSuccessful();

    void onLoginFailed(@ErrorMessages int errorCode);
}
