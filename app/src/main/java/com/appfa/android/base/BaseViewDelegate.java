package com.appfa.android.base;

import android.app.Activity;

import com.appfa.android.annotation.ErrorMessages;
import com.appfa.android.utils.KeyboardUtils;

public abstract class BaseViewDelegate {
    protected final KeyboardUtils keyboardUtils;

    public BaseViewDelegate(Activity activity) {
        this.keyboardUtils = new KeyboardUtils(activity);
    }

    protected abstract void showErrorMessage(@ErrorMessages int errorMessage);

    protected interface BaseDelegateCallback {
        Activity getActivity();
    }
}
