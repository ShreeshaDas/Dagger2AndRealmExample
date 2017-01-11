package com.example.shreesha.basecode.Network;

import android.content.Context;

import com.example.shreesha.basecode.BuildConfig;
import com.example.shreesha.basecode.Config;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ennur on 6/28/16.
 */
@Module
public class NetworkModule {

    private Context mContext;

    public NetworkModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    @Named("RxParsing")
    Retrofit provideCall() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Response response = chain.proceed(original);
                        response.cacheResponse();
                        return response;
                    }
                })
                .build();


        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("SimpleParsing")
    Retrofit provideRetrofit() {


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                response.cacheResponse();
                return response;
            }
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        OkHttpClient okHttpClient = builder.build();

        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("RxInterface")
    public ApiInterface providesApiRxInterface(
            @Named("RxParsing") Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    @Named("SimpleInterface")
    public ApiInterface providesApiSimpleInterface(
            @Named("SimpleParsing") Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    @Named("RxService")
    public Service providesRxService(
            @Named("RxInterface") ApiInterface apiInterface) {
        return new Service(apiInterface);
    }


    @Provides
    @Singleton
    @Named("SimpleService")
    public Service providesSimpleService(
            @Named("SimpleInterface") ApiInterface apiInterface) {
        return new Service(apiInterface);
    }

    @Provides
    @Singleton
    public NetworkUtils provideNetworkUtils() {
        return new NetworkUtils(mContext);
    }

}
