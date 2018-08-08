package com.appfa.android.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.appfa.android.annotation.CustomTypefaces;

public final class TextBaseUtils {

    private TextBaseUtils() {

    }

    public static Typeface getCustomTypeface(Context context, @CustomTypefaces String typefaceName) {
        return Typeface.createFromAsset(context.getAssets(), typefaceName);
    }
}
