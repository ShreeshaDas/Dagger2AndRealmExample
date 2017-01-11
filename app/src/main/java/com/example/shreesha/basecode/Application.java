package com.example.shreesha.basecode;

import android.content.Context;

import com.example.shreesha.basecode.Data.DataModule;
import com.example.shreesha.basecode.Network.NetworkModule;
import com.example.shreesha.basecode.RxBus.RxBusModule;

/**
 * Created by shreesha on 29/12/16.
 */

public class Application extends android.app.Application {

    public AppComponent mAppComponent;


    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(getApplicationContext()))
                .rxBusModule(new RxBusModule())
                .dataModule(new DataModule(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
