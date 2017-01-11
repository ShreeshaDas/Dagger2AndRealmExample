package com.example.shreesha.basecode.Ui.PhotosList;

import android.support.v7.widget.LinearLayoutManager;

import com.example.shreesha.basecode.CustomScope.PerFragment;
import com.example.shreesha.basecode.Data.DatabaseHelper;
import com.example.shreesha.basecode.Models.Photo;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shreesha on 4/1/17.
 */
@Module
public class PhotosListModule {

    private PhotosListContract.View view;
    private PhotosListAdapter.OnItemClick mOnItemClick;

    public PhotosListModule(PhotosListContract.View view, PhotosListAdapter.OnItemClick onItemClick) {
        this.view = view;
        this.mOnItemClick = onItemClick;
    }


    @Provides
    @PerFragment
    public PhotosListAdapter providePhotosListAdapter() {
        return new PhotosListAdapter(view.getContext(), new ArrayList<Photo>(), mOnItemClick);
    }

    @Provides
    @PerFragment
    public PhotosListPresenter providePhotosListPresenter(PhotosListAdapter photosListAdapter, DatabaseHelper databaseHelper) {
        return new PhotosListPresenter(view, photosListAdapter, databaseHelper);
    }


    @Provides
    @PerFragment
    public LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(view.getContext());
    }


}
