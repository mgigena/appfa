package com.appfa.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.appfa.android.base.view.BaseView;
import com.appfa.android.base.view.MvpAdapter;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class AppFaBaseActivity<V extends BaseView, P extends BasePresenter<V>>
        extends AppCompatActivity implements MvpAdapter<V, P> {

    private Unbinder unbinder;
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(getMvpView());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    public abstract P createPresenter();

    @NonNull
    public P getPresenter() {
        if (presenter == null) {
            presenter = createPresenter();
        }
        return presenter;
    }
}
