package com.appfa.android.base.view;

import android.support.annotation.NonNull;

import com.appfa.android.base.BasePresenter;

public interface MvpAdapter <V extends BaseView, P extends BasePresenter<V>> {

    /**
     * Retrieves the MVP view on which the presenter should work. Usually it's this very activity.
     *
     * @return The MVP View on which to work.
     */
    @NonNull
    V getMvpView();
}
