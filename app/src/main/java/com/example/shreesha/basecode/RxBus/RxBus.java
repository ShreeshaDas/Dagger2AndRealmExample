package com.example.shreesha.basecode.RxBus;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by shreesha on 2/1/17.
 */

public class RxBus {

    private final Subject<Object, Object> bus;
    private Subscription mSubscription;

    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public void send(Object o) {
        bus.onNext(o);
    }

    public void addObserver(Action1 action1) {
        mSubscription = bus
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    public void removeObserver() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
