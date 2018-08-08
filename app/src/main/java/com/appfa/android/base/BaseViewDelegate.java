package com.appfa.android.base;

import android.app.Activity;
import android.view.View;

import com.appfa.android.annotation.ErrorMessages;
import com.appfa.android.utils.KeyboardUtils;

import butterknife.ButterKnife;

public abstract class BaseViewDelegate {
    protected final KeyboardUtils keyboardUtils;

    public BaseViewDelegate(View view, BaseDelegateCallback callback) {
        this.keyboardUtils = new KeyboardUtils(callback.getActivity());
        ButterKnife.bind(this, view);
    }

    protected abstract void showErrorMessage(@ErrorMessages int errorMessage);

    protected interface BaseDelegateCallback {
        Activity getActivity();
    }
}
