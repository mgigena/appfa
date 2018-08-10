package com.appfa.android.main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appfa.android.R;
import com.appfa.android.base.BaseViewDelegate;

import java.lang.ref.SoftReference;

import butterknife.BindView;

public class MainViewDelegate extends BaseViewDelegate {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.addButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navView;

    private final SoftReference<AppCompatActivity> activity;

    public MainViewDelegate(View view, MainDelegateCallback callback) {
        super(view, callback);
        activity = new SoftReference(callback.getActivity());

        configureToolbar();
        configureNavigationDrawer();
        setAddButtonConfig();
    }

    private void configureToolbar() {
        AppCompatActivity appCompatActivity = activity.get();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
            ActionBar actionbar = appCompatActivity.getSupportActionBar();
            actionbar.setHomeAsUpIndicator(R.mipmap.ic_burger);
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureNavigationDrawer() {
        AppCompatActivity appCompatActivity = activity.get();
        if (appCompatActivity != null) {
            if (appCompatActivity instanceof NavigationView.OnNavigationItemSelectedListener) {
                NavigationView.OnNavigationItemSelectedListener listener = (NavigationView.OnNavigationItemSelectedListener) appCompatActivity;
                navView.getMenu().getItem(0).setChecked(true);
                listener.onNavigationItemSelected(navView.getMenu().getItem(0));
                navView.setNavigationItemSelectedListener(listener);
            }
        }
    }

    private void setAddButtonConfig() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO tener en cuenta la seleccion del drawer
            }
        });
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    public boolean isDrawerOpen() {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    protected void showErrorMessage(int errorMessage) {

    }

    interface MainDelegateCallback extends BaseDelegateCallback {

    }
}
