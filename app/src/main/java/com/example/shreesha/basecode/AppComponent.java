package com.example.shreesha.basecode;

import com.example.shreesha.basecode.Data.DataModule;
import com.example.shreesha.basecode.RxBus.RxBusModule;
import com.example.shreesha.basecode.Network.NetworkModule;
import com.example.shreesha.basecode.Ui.Common.BaseActivity;
import com.example.shreesha.basecode.Ui.Home.HomeActivity;
import com.example.shreesha.basecode.Ui.Home.HomeComponent;
import com.example.shreesha.basecode.Ui.Home.HomeModule;
import com.example.shreesha.basecode.Ui.PhotoDetail.PhotoDetailComponent;
import com.example.shreesha.basecode.Ui.PhotoDetail.PhotoDetailModule;
import com.example.shreesha.basecode.Ui.PhotosList.PhotosListComponent;
import com.example.shreesha.basecode.Ui.PhotosList.PhotosListFragment;
import com.example.shreesha.basecode.Ui.PhotosList.PhotosListModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shreesha on 3/1/17.
 */

@Singleton
@Component(modules = {NetworkModule.class, RxBusModule.class, DataModule.class})
public interface AppComponent {
    HomeComponent plus(HomeModule homeModule);
    PhotosListComponent plus(PhotosListModule photosListModule);
    PhotoDetailComponent plus(PhotoDetailModule photoDetailModule);
}
