package com.example.shreesha.basecode.Ui.PhotosList;

import com.example.shreesha.basecode.CustomScope.PerFragment;

import dagger.Subcomponent;

/**
 * Created by shreesha on 4/1/17.
 */
@PerFragment
@Subcomponent(modules = PhotosListModule.class)
public interface PhotosListComponent {
    void inject(PhotosListFragment photosListFragment);
}
