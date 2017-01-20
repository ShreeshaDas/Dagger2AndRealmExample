package com.example.shreesha.basecode.Ui.PhotosList;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.shreesha.basecode.Data.DatabaseHelper;
import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.Models.PhotoResponse;
import com.example.shreesha.basecode.Network.CacheType;
import com.example.shreesha.basecode.Network.NetworkError;
import com.example.shreesha.basecode.Network.NetworkUtils;
import com.example.shreesha.basecode.Network.ResponseCallback;
import com.example.shreesha.basecode.Network.Service;
import com.example.shreesha.basecode.Ui.Common.LoadMoreItemRecyclerViewScrollListener;

import java.util.List;

import retrofit2.Call;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotosListPresenter implements PhotosListContract.Presenter, LoadMoreItemRecyclerViewScrollListener.OnPagination, SwipeRefreshLayout.OnRefreshListener {

    private PhotosListContract.View mView;
    private PhotosListAdapter mPhotosListAdapter;
    private RecyclerView mRecyclerView;
    private Service mService;
    private String mCategory;
    private LoadMoreItemRecyclerViewScrollListener mLoadMoreItemRecyclerViewScrollListener;
    private DatabaseHelper mDatabaseHelper;
    private NetworkUtils mNetworkUtils;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public PhotosListPresenter(PhotosListContract.View mView, PhotosListAdapter photosListAdapter, DatabaseHelper databaseHelper, NetworkUtils networkUtils) {
        this.mView = mView;
        this.mPhotosListAdapter = photosListAdapter;
        this.mDatabaseHelper = databaseHelper;
        this.mNetworkUtils = networkUtils;
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
    public void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void fetchPhotos(String category, Service service) {
        mService = service;
        mCategory = category;
        if (mNetworkUtils.isNetworkConnected()) {
            mView.showNoInternetDialog();
        }
        if (TextUtils.isEmpty(category)) {
            mView.showEmptyView();
        } else {
            getPhotos(category, 1);
            setPagination();
        }

    }

    public void setPagination() {
        mLoadMoreItemRecyclerViewScrollListener = new LoadMoreItemRecyclerViewScrollListener((LinearLayoutManager) mRecyclerView.getLayoutManager(), this);
        mRecyclerView.addOnScrollListener(mLoadMoreItemRecyclerViewScrollListener);
    }


    @Override
    public void resetPagination() {
        mRecyclerView.scrollToPosition(0);
        mPhotosListAdapter.clearData();
        mLoadMoreItemRecyclerViewScrollListener.reset();
    }

    private void getPhotos(final String categoryName, final int current_page) {
        mView.showProgress(true);
        mService.getPhotos(mNetworkUtils.setCacheType(CacheType.NETWORK_AND_CACHE), categoryName, current_page, new ResponseCallback<PhotoResponse>() {
            @Override
            public void success(PhotoResponse photoResponse) {
                mSwipeRefreshLayout.setRefreshing(false);
                mView.showProgress(false);
                updatePhotoList(photoResponse, current_page);
                mDatabaseHelper.savePhotoList(categoryName, photoResponse.getPhotos());
            }

            @Override
            public void failure(Call call, NetworkError error) {
                mSwipeRefreshLayout.setRefreshing(false);
                mView.showProgress(false);
            }

            @Override
            public void onTimeOut(Call call) {
                mSwipeRefreshLayout.setRefreshing(false);
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
        if (!mNetworkUtils.isNetworkConnected()) return;
        getPhotos(mCategory, ++current_page);
    }

    @Override
    public void onRefresh() {
        if (mNetworkUtils.isNetworkConnected()) {
            getPhotos(mCategory, 1);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
