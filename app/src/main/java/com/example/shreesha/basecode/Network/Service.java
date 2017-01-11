package com.example.shreesha.basecode.Network;


import com.example.shreesha.basecode.Config;
import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.Models.PhotoResponse;

import javax.inject.Inject;

import retrofit2.*;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by ennur on 6/25/16.
 */
public class Service {
    private final ApiInterface apiInterface;


    public Service(ApiInterface networkService) {
        this.apiInterface = networkService;
    }

    /**
     * RxJava Parsing
     */
    /*public Subscription getPhotos(final ResponseCallback callback) {

        return apiInterface.getTodayFavPhotos()
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
    public void getPhotos(String category, int page, ResponseCallback callback) {
        apiInterface.getCategoryPhotos(category, Config.CONSUMER_KEY, page).enqueue(callback);
    }

}
