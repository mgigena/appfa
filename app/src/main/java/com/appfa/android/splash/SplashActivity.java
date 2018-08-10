package com.appfa.android.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.appfa.android.R;
import com.appfa.android.login.LoginActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Por ahora no depende de base activity
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this,
                        LoginActivity.class)); // TODO verificar si el usuario esta logueado previamente
                finish();
            }
        }, secondsDelayed * 1000);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
