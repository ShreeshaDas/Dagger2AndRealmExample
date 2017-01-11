package com.example.shreesha.basecode.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shreesha on 2/1/17.
 */
@Module
public class RxBusModule {

    public RxBusModule() {
    }

    @Provides
    @Singleton
    public RxBus providesRxBus() {
        return new RxBus();
    }

}
