package com.example.shreesha.basecode.Data;

import com.example.shreesha.basecode.Models.Photo;

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

    public void savePhotoList(List<Photo> photos) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(photos);
        mRealm.createObject(Photo.class);
        mRealm.commitTransaction();
    }

    public Photo getPhoto(Integer id) {
        return mRealm.where(Photo.class)
                .equalTo("id", id)
                .findFirst();
    }
}
