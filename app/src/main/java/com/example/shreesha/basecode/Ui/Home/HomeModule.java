package com.example.shreesha.basecode.Ui.Home;

import com.example.shreesha.basecode.CustomScope.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shreesha on 4/1/17.
 */
@Module
public class HomeModule {

    HomeContract.View mHomeView;

    public HomeModule(HomeContract.View homeView) {
        this.mHomeView = homeView;
    }

    @Provides
    @ActivityScope
    public HomePresenter provideHomePresenter() {
        return new HomePresenter(mHomeView);
    }
}
