package com.appfa.android.login;

import android.support.annotation.NonNull;

import com.appfa.android.base.view.BasePresenterView;

public interface LoginPresenterContract extends BasePresenterView {

    void attempLogin(@NonNull String userName, @NonNull String password);
}
