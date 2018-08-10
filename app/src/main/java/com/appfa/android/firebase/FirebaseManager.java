package com.appfa.android.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.appfa.android.model.dto.UserDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.appfa.android.annotation.ErrorMessages.GENERIC_ERROR;
import static com.appfa.android.annotation.ErrorMessages.INVALID_CREDENTIALS;
import static com.appfa.android.annotation.ErrorMessages.USER_ALREADY_EXITS;
import static com.appfa.android.annotation.ErrorMessages.WEAK_PASSWORD;
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
                            int errorCode;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                errorCode = WEAK_PASSWORD;
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                errorCode = INVALID_CREDENTIALS;
                            } catch (FirebaseAuthUserCollisionException e) {
                                errorCode = USER_ALREADY_EXITS;
                            } catch (FirebaseAuthInvalidUserException e) {
                                errorCode = INVALID_CREDENTIALS;
                            }catch (Exception e) {
                                errorCode = GENERIC_ERROR;
                            }
                            callback.onLoginFailed(errorCode);
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
