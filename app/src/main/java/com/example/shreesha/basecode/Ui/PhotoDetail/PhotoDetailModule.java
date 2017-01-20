package com.example.shreesha.basecode.Ui.PhotoDetail;

import com.example.shreesha.basecode.CustomScope.PerFragment;
import com.example.shreesha.basecode.Data.DatabaseHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shreesha on 3/1/17.
 */

@Module
public class PhotoDetailModule {

    private PhotoDetailContract.View mView;

    public PhotoDetailModule(PhotoDetailContract.View view) {
        this.mView = view;
    }


    @PerFragment
    @Provides
    public PhotoDetailPresenter providePhotoDetailPresenter(DatabaseHelper databaseHelper) {
        return new PhotoDetailPresenter(mView, databaseHelper);
    }

}
