package com.example.shreesha.basecode.Data;

import android.util.Log;

import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.Models.PhotoResponse;

import java.util.List;

import io.realm.Realm;

/**
 * Created by shreesha on 10/1/17.
 */

public class DatabaseHelper {

    private Realm mRealm;

    public DatabaseHelper(Realm mRealm) {
        this.mRealm = mRealm;
    }

    public void savePhotoList(String categoryName, List<Photo> photos) {
        mRealm.beginTransaction();
        setCategoryToPhotos(categoryName, photos);
        mRealm.copyToRealmOrUpdate(photos);
        mRealm.createObject(Photo.class);
        mRealm.commitTransaction();
    }

    private void setCategoryToPhotos(String categoryName, List<Photo> photos) {
        if (photos != null && !photos.isEmpty()) {
            for (Photo photo : photos) {
                photo.setType(categoryName);
            }
        }
    }

    public Photo getPhoto(Integer id) {
        return mRealm.where(Photo.class)
                .equalTo("id", id)
                .findFirst();
    }

    public List<Photo> getPhotos(String category) {
        return mRealm.where(Photo.class)
                .equalTo("type", category)
                .findAll();
    }

}
