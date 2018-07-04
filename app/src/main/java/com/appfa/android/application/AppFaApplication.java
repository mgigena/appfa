package com.appfa.android.application;

import android.app.Application;

import com.appfa.android.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppFaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath).build());
    }
}
