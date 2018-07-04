package com.appfa.android.login;

import android.support.annotation.NonNull;

import com.appfa.android.login.firebase.FirebaseLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseManager {

    public static final FirebaseManager instance = new FirebaseManager();

    private final FirebaseAuth firebaseAuth;

    private FirebaseManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseManager getInstance() {
        return instance;
    }

    public void attempLogin(@NonNull String userName, @NonNull String password, @NonNull final FirebaseLogin.AttempLoginCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onLoginSuccessful();
                        } else {
                            callback.onLoginFailed();
                        }
                    }
                });
    }

    public void checkIfUserIsLogged(@NonNull FirebaseLogin.ChechUserCallback callback) {
        callback.isUserLogged(firebaseAuth.getCurrentUser() != null);
    }
}
