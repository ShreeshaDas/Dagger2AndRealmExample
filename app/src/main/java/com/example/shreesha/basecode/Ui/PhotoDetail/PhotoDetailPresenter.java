package com.example.shreesha.basecode.Ui.PhotoDetail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shreesha.basecode.Data.DatabaseHelper;
import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.R;
import com.example.shreesha.basecode.Ui.PhotosList.PhotosListContract;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {

    private DatabaseHelper mDatabaseHelper;
    private PhotoDetailContract.View mView;
    private Photo mPhoto;

    public PhotoDetailPresenter(PhotoDetailContract.View view, DatabaseHelper databaseHelper) {
        this.mDatabaseHelper = databaseHelper;
        this.mView = view;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onResume() {
        if (mPhoto != null && !TextUtils.isEmpty(mPhoto.getName())) {
            mView.setCollapsingToolbarLayoutTitle(mPhoto.getName());
        }

        if (mPhoto != null && !TextUtils.isEmpty(mPhoto.getImages().get(1).getUrl()) && !TextUtils.isEmpty(mPhoto.getName())) {
            mView.setPhotoDescription(mPhoto.getImages().get(1).getUrl(), getPhotoDescription());
        }

        String userAvatarUrl = null;
        String userName = null;
        String userCountry = null;
        if (mPhoto != null && mPhoto.getUser() != null && mPhoto.getUser().getAvatars() != null && mPhoto.getUser().getAvatars().getSmall() != null && !TextUtils.isEmpty(mPhoto.getUser().getAvatars().getSmall().getHttps())) {
            userAvatarUrl = mPhoto.getUser().getAvatars().getSmall().getHttps();
        }

        if (mPhoto != null && mPhoto.getUser() != null && !TextUtils.isEmpty(mPhoto.getUser().getCountry())) {
            userCountry = mPhoto.getUser().getCountry();
        }

        if (mPhoto != null && mPhoto.getUser() != null && !TextUtils.isEmpty(mPhoto.getUser().getUsername())) {
            userName = mPhoto.getUser().getUsername();
        }

        mView.setUserDetail(userAvatarUrl, userName, userCountry);
    }

    private String getPhotoDescription() {
        String description = TextUtils.isEmpty(mPhoto.getDescription()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getDescription();
        String camera = TextUtils.isEmpty(mPhoto.getCamera()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getCamera();
        String lens = TextUtils.isEmpty(mPhoto.getLens()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getLens();
        String focal_length = TextUtils.isEmpty(mPhoto.getFocal_length()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getFocal_length();
        String iso = TextUtils.isEmpty(mPhoto.getIso()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getIso();
        String shutter_speed = TextUtils.isEmpty(mPhoto.getShutter_speed()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getShutter_speed();
        String aperture = TextUtils.isEmpty(mPhoto.getAperture()) ? mView.getContext().getResources().getString(R.string.na) : mPhoto.getAperture();
        return String.format(mView.getContext().getResources().getString(R.string.image_description), description, camera, lens, focal_length, iso, shutter_speed, aperture);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getPhotoById(int photoId) {
        mPhoto = mDatabaseHelper.getPhoto(photoId);
    }

}
