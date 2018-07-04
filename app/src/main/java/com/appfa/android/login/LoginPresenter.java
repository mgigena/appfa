package com.appfa.android.login;

import android.support.annotation.NonNull;

import com.appfa.android.base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter extends BasePresenter<LoginView> implements LoginPresenterContract {

    private FirebaseAuth firebaseAuth;

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
        checkIfUserExists();
    }

    @Override
    public void attempLogin(@NonNull String userName, @NonNull String password) {
        firebaseAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (isViewAttached()) {
                            if (task.isSuccessful()) {
                                //FirebaseUser user = mAuth.getCurrentUser();
                                getView().onLoginSuccessful();
                            } else {
                                getView().onLoginFailed();
                            }
                        }
                    }
                });
    }

    private void checkIfUserExists() {
        firebaseAuth = FirebaseAuth.getInstance();
        if (isViewAttached()) {
            getView().isUserLogged(firebaseAuth.getCurrentUser() != null);
        }
    }
}
