package com.example.shreesha.basecode.Network;

import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.Models.PhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by shreesha on 30/12/16.
 */

public interface ApiInterface {

    @GET("v1/photos?feature=fresh_today&sort=created_at&rpp=1&image_size=3&include_store=store_download&include_states=voted&consumer_key=" + Constants.CONSUMER_KEY)
    Observable<PhotoResponse> getTodayFavPhotos();


    @GET("v1/photos?exclude=Nude&sort=created_at&image_size=440,600&include_store=store_download&include_states=voted")
    Call<PhotoResponse> getCategoryPhotos(@Query("only") String category, @Query("consumer_key") String apiKey, @Query("page") int page);
}
