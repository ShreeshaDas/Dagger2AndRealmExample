package com.example.shreesha.basecode.Ui.PhotoDetail;

import com.example.shreesha.basecode.CustomScope.PerFragment;

import dagger.Subcomponent;

/**
 * Created by shreesha on 3/1/17.
 */
@PerFragment
@Subcomponent(modules = {PhotoDetailModule.class})
public interface PhotoDetailComponent {
    void inject(PhotoDetailFragment photoDetailFragment);
}
