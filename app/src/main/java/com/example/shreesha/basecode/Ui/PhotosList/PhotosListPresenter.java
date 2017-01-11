package com.example.shreesha.basecode.Ui.PhotosList;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.shreesha.basecode.Data.DatabaseHelper;
import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.Models.PhotoResponse;
import com.example.shreesha.basecode.Network.NetworkError;
import com.example.shreesha.basecode.Network.NetworkUtils;
import com.example.shreesha.basecode.Network.ResponseCallback;
import com.example.shreesha.basecode.Network.Service;
import com.example.shreesha.basecode.Ui.Common.LoadMoreItemRecyclerViewScrollListener;

import retrofit2.Call;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotosListPresenter implements PhotosListContract.Presenter, LoadMoreItemRecyclerViewScrollListener.OnPagination {

    private PhotosListContract.View mView;
    private PhotosListAdapter mPhotosListAdapter;
    private RecyclerView mRecyclerView;
    private Service mService;
    private String mCategory;
    private LoadMoreItemRecyclerViewScrollListener mLoadMoreItemRecyclerViewScrollListener;
    private DatabaseHelper mDatabaseHelper;


    public PhotosListPresenter(PhotosListContract.View mView, PhotosListAdapter photosListAdapter, DatabaseHelper databaseHelper) {
        this.mView = mView;
        this.mPhotosListAdapter = photosListAdapter;
        this.mDatabaseHelper = databaseHelper;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void initAdapter(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mPhotosListAdapter);
    }

    @Override
    public void fetchPhotos(String category, Service service, NetworkUtils networkUtils) {
        mService = service;
        mCategory = category;
        if (networkUtils.isNetworkConnected()) {
            if (TextUtils.isEmpty(category)) {
                mView.showEmptyView();
            } else {
                getPhotos(category, 1);
                setPagination();
            }
        } else {
            mView.showNoInternetDialog();
        }
    }

    public void setPagination() {
        mLoadMoreItemRecyclerViewScrollListener = new LoadMoreItemRecyclerViewScrollListener((LinearLayoutManager) mRecyclerView.getLayoutManager(), this);
        mRecyclerView.addOnScrollListener(mLoadMoreItemRecyclerViewScrollListener);
    }


    @Override
    public void resetPagination() {
        mPhotosListAdapter.clearData();
        mLoadMoreItemRecyclerViewScrollListener.reset();
    }

    private void getPhotos(String categoryName, final int current_page) {
        mView.showProgress(true);
        mService.getPhotos(categoryName, current_page, new ResponseCallback<PhotoResponse>() {
            @Override
            public void success(PhotoResponse photoResponse) {
                mView.showProgress(false);
                updatePhotoList(photoResponse, current_page);
                mDatabaseHelper.savePhotoList(photoResponse.getPhotos());
            }

            @Override
            public void failure(Call call, NetworkError error) {
                mView.showProgress(false);
            }

            @Override
            public void onTimeOut(Call call) {
                mView.showProgress(false);
            }
        });
    }

    private void updatePhotoList(PhotoResponse photoResponse, int current_page) {
        if (current_page > 1) {
            mPhotosListAdapter.addData(photoResponse.getPhotos());
        } else {
            mPhotosListAdapter.setData(photoResponse.getPhotos());
        }

    }

    @Override
    public void onLoadMore(int current_page) {
        getPhotos(mCategory, ++current_page);
    }
}
