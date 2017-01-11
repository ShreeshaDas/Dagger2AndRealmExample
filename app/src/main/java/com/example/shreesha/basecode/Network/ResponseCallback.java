package com.example.shreesha.basecode.Network;

import java.net.SocketTimeoutException;

import retrofit2.*;

/**
 * Created by shreesha on 30/12/16.
 */

public abstract class ResponseCallback<T> implements Callback<T> {

    public abstract void success(T t);

    public abstract void failure(Call<T> call, NetworkError error);

    public abstract void onTimeOut(Call<T> call);

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        if (response != null && response.isSuccessful()) {
            success(response.body());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            onTimeOut(call);
        } else {
            failure(call, new NetworkError(t));
        }
    }
}
