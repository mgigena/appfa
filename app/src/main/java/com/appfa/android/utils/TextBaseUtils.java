package com.appfa.android.utils;

import android.support.annotation.NonNull;

public final class TextBaseUtils {

    private static final String EMAIL_SUFIX = "@appfa.com";

    private TextBaseUtils() {

    }

    public static String getFormattedUser(@NonNull String user) {
        return user + EMAIL_SUFIX;
    }
}
