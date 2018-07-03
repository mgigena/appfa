package com.appfa.android.base;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.appfa.android.base.view.BasePresenterView;
import com.appfa.android.base.view.BaseView;

import java.lang.ref.SoftReference;

public class BasePresenter<V extends BaseView> implements BasePresenterView {

    private SoftReference<V> view;

    @UiThread
    public void attachView(V view) {
        this.view = new SoftReference<>(view);
    }

    public void detachView(){
        if(view != null) {
            view.clear();
            view = null;
        }
    }

    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @Nullable
    public V getView() {
        return view.get();
    }
}
