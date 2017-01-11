package com.example.shreesha.basecode.Data;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shreesha on 10/1/17.
 */
@Module
public class DataModule {

    private static final String SHARED_PREFERENCE_FILE_NAME = "basecode";

    private Context mContext;

    public DataModule(Context mContext) {
        this.mContext = mContext;
    }


    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return mContext.getSharedPreferences(
                SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public PreferenceHelper providePreferenceHelper(SharedPreferences sharedPreferences) {
        return new PreferenceHelper(sharedPreferences);
    }

    @Provides
    @Singleton
    public DatabaseHelper provideRealm() {

        // Initialize Realm
        Realm.init(mContext);
        // configure Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        return new DatabaseHelper(realm);
    }
}
