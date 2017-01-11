package com.example.shreesha.basecode.Ui.PhotoDetail;

import com.example.shreesha.basecode.AppComponent;
import com.example.shreesha.basecode.CustomScope.CustomComponent;

import dagger.Component;

/**
 * Created by shreesha on 3/1/17.
 */
@CustomComponent
@Component(modules = {PhotoDetailModule.class}, dependencies = {AppComponent.class})
public interface PhotoDetailComponent {
    void inject(PhotoDetailFragment photoDetailFragment);
}
