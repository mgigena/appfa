package com.appfa.android.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.appfa.android.model.dto.UserDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.appfa.android.annotation.FirebaseDatabaseNames.USER_DATABASE;


// TODO
// {
//  "rules": {
//    ".read": false,
//    ".write": false
//  }
//}

public class FirebaseManager {

    public static final FirebaseManager instance = new FirebaseManager();

    private FirebaseAuth firebaseAuth;
    private final DatabaseReference databaseReference;

    private FirebaseManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
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
                            // TODO task.getException();
                            callback.onLoginFailed();
                        }
                    }
                });
    }

    public void checkIfUserIsLogged(@NonNull FirebaseLogin.CheckUserCallback callback) {
        callback.isUserLogged(firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().getIdToken(false) != null);
    }

    public void registerUser(final @NonNull UserDTO user, final FirebaseLogin.RegisterUserCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserData(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            // TODO Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            saveUserData(user);
                        }
                    }
                });
    }

    private void saveUserData(final @NonNull UserDTO user) {
        databaseReference.child(USER_DATABASE).child(firebaseAuth.getUid()).setValue(user).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("mauricio", "registro exitoso");
                        } else {
                            Log.d("mauricio", "registro fallido: " + task.getException());
                        }
                    }
                });
    }
}
