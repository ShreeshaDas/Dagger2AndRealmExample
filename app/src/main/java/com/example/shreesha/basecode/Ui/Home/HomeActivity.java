package com.example.shreesha.basecode.Ui.Home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.shreesha.basecode.Application;
import com.example.shreesha.basecode.Data.PreferenceHelper;
import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.RxBus.RxBus;
import com.example.shreesha.basecode.Ui.Common.BaseActivity;
import com.example.shreesha.basecode.Models.PhotoResponse;
import com.example.shreesha.basecode.R;
import com.example.shreesha.basecode.Ui.Common.BaseFragment;
import com.example.shreesha.basecode.Ui.PhotosList.PhotosListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by shreesha on 30/12/16.
 */


public class HomeActivity extends BaseActivity implements HomeContract.View, Action1<Object> {


    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;

    @BindView(R.id.content_frame)
    public FrameLayout mFrameLayout;

    @BindView(R.id.left_drawer)
    public ListView mListView;

    private ActionBarDrawerToggle mDrawerToggle;

    @Inject
    HomePresenter mHomePresenter;

    @Inject
    public RxBus mRxBus;

    @Inject
    public PreferenceHelper mPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        mHomePresenter.onCreate();
        initDrawerListener();
        mHomePresenter.setLeftDrawerAdapter(mListView);
    }

    private void initDrawerListener() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void setupActivityComponent() {
        Application.get(this).getAppComponent().plus(new HomeModule(this)).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRxBus.addObserver(this);
        mRxBus.send(new PhotoResponse());
        mPreferenceHelper.saveBooleanIntoSharedPreference(Constants.PREFERENCE, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRxBus.removeObserver();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("Home", "" + mPreferenceHelper.getBooleanFromSharedPreference(Constants.PREFERENCE));
    }

    @Override
    public void call(Object object) {
        Log.d("Bus", "success");
        if (object instanceof PhotoResponse) {
            Log.d("Bus", "success PhotoResponse");
        }
    }

    @Override
    public void showProgress(boolean status) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showPopupMessage(String title, String message) {

    }

    @Override
    public void showNoInternetDialog() {

    }

    @Override
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void closeLeftDrawer() {
        mDrawerLayout.closeDrawer(mListView);
    }

    @Override
    public void loadPhotoListFragment(String category) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment == null) {
            loadFragment(R.id.content_frame, PhotosListFragment.create(category), null, 0, 0,
                    BaseFragment.FragmentTransactionType.CLEAR_BACK_STACK_AND_REPLACE);
        } else if (fragment instanceof PhotosListFragment) {
            ((PhotosListFragment) fragment).getPhotosOfSelectedCategory(category);
        }

    }

    @Override
    public void updateLeftDrawer(int position) {
        mListView.setItemChecked(position, true);
    }
}
