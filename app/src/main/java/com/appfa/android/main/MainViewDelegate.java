package com.appfa.android.main;

import android.view.View;

import com.appfa.android.base.BaseViewDelegate;

public class MainViewDelegate extends BaseViewDelegate {


    public MainViewDelegate(View view, MainDelegateCallback callback) {
        super(view, callback);
    }

    @Override
    protected void showErrorMessage(int errorMessage) {

    }

    interface MainDelegateCallback extends BaseDelegateCallback {

    }
}
