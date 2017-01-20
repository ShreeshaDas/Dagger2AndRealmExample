package com.example.shreesha.basecode.Network;


import com.example.shreesha.basecode.Config;
import com.example.shreesha.basecode.Data.DatabaseHelper;
import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.Models.PhotoResponse;

import java.util.List;

/**
 * Created by ennur on 6/25/16.
 */
public class Service {
    private ApiInterface mApiInterface;
    private NetworkUtils mNetworkUtils;
    private DatabaseHelper mDatabaseHelper;


    public Service(ApiInterface networkService, NetworkUtils networkUtils, DatabaseHelper databaseHelper) {
        this.mApiInterface = networkService;
        this.mNetworkUtils = networkUtils;
        this.mDatabaseHelper = databaseHelper;
    }

    /**
     * RxJava Parsing
     */
    /*public Subscription getPhotos(final ResponseCallback callback) {

        return mApiInterface.getTodayFavPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends PhotoResponse>>() {
                    @Override
                    public Observable<? extends PhotoResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<PhotoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failure(new NetworkError(e));

                    }

                    @Override
                    public void onNext(PhotoResponse cityListResponse) {
                        callback.success(cityListResponse);

                    }
                });
    }*/
    public void getPhotos(CacheType cacheType, String category, int page, ResponseCallback callback) {
        switch (cacheType) {
            case NETWORK:
                mApiInterface.getCategoryPhotos(category, Config.CONSUMER_KEY, page).enqueue(callback);
                break;
            case CACHE:
                List<Photo> photos = mDatabaseHelper.getPhotos(category);
                if (photos == null && photos.isEmpty()) {
                    mApiInterface.getCategoryPhotos(category, Config.CONSUMER_KEY, page).enqueue(callback);
                } else {
                    fetchPhotosFromCache(photos, page, callback);
                }
                break;
            case NETWORK_AND_CACHE:
                List<Photo> photos1 = mDatabaseHelper.getPhotos(category);
                if (photos1 != null && !photos1.isEmpty()) {
                    fetchPhotosFromCache(photos1, page, callback);
                }
                mApiInterface.getCategoryPhotos(category, Config.CONSUMER_KEY, page).enqueue(callback);
                break;
        }
    }

    private void fetchPhotosFromCache(List<Photo> photos, int page, ResponseCallback callback) {
        if (page > 1) return;
        PhotoResponse photoResponse1 = new PhotoResponse();
        photoResponse1.setPhotos(photos);
        callback.success(photoResponse1);
    }

}
