package com.example.shreesha.basecode.Ui.Home;

import com.example.shreesha.basecode.CustomScope.ActivityScope;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by shreesha on 4/1/17.
 */
@ActivityScope
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
