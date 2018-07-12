package com.appfa.android.login;

import android.support.annotation.NonNull;

import com.appfa.android.base.BasePresenter;
import com.appfa.android.firebase.FirebaseLogin;
import com.appfa.android.firebase.FirebaseManager;

public class LoginPresenter extends BasePresenter<LoginView> implements LoginPresenterContract {

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
        checkIfUserExists();
    }

    @Override
    public void attempLogin(@NonNull String userName, @NonNull String password) {
        final FirebaseLogin.AttempLoginCallback callback = new FirebaseLogin.AttempLoginCallback() {
            @Override
            public void onLoginSuccessful() {
                if (isViewAttached()) {
                    //FirebaseUser user = mAuth.getCurrentUser();
                    getView().onLoginSuccessful();
                }
            }

            @Override
            public void onLoginFailed() {
                if (isViewAttached()) {
                    getView().onLoginFailed();
                }
            }
        };

        FirebaseManager.getInstance().attempLogin(userName, password, callback);
    }

    private void checkIfUserExists() {
        final FirebaseLogin.CheckUserCallback callback = new FirebaseLogin.CheckUserCallback() {
            @Override
            public void isUserLogged(boolean isLogged) {
                if (isViewAttached()) {
                    getView().isUserLogged(isLogged);
                }
            }
        };
        FirebaseManager.getInstance().checkIfUserIsLogged(callback);
    }
}
