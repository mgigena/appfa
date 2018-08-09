package com.appfa.android.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.appfa.android.R;
import com.appfa.android.base.AppFaBaseActivity;
import com.appfa.android.model.dto.TeamDTO;
import com.appfa.android.team.TeamFragment;

public class MainActivity extends AppFaBaseActivity<MainView, MainPresenter> implements MainView,
        NavigationView.OnNavigationItemSelectedListener,
        TeamFragment.OnListFragmentInteractionListener, MainViewDelegate.MainDelegateCallback {

    private MainViewDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        delegate = new MainViewDelegate(getWindow().getDecorView().getRootView(), this);
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            // Android home
            case android.R.id.home:
                delegate.openDrawer();
                return true;
            // manage other entries if you have it ...
        }
        return true;
    }

    @Override
    public void onListFragmentInteraction(TeamDTO item) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.teams) {
            fragment = TeamFragment.newInstance(1);
        } else if (itemId == R.id.tournaments) {
            //f = new StopFragment();
        }
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.commit();
            if(delegate != null) {
                delegate.closeDrawer();
            }
            return true;
        }
        return false;
    }

    @NonNull
    @Override
    public MainView getMvpView() {
        return this;
    }

    @Override
    public void onBackPressed() {
        if (delegate.isDrawerOpen()) {
            delegate.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
