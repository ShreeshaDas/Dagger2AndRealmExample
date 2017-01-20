package com.example.shreesha.basecode.Ui.PhotosList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.shreesha.basecode.Application;
import com.example.shreesha.basecode.Data.DatabaseHelper;
import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.Network.NetworkUtils;
import com.example.shreesha.basecode.Network.Service;
import com.example.shreesha.basecode.R;
import com.example.shreesha.basecode.Ui.Common.BaseFragment;
import com.example.shreesha.basecode.Ui.PhotoDetail.PhotoDetailFragment;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotosListFragment extends BaseFragment implements PhotosListContract.View, PhotosListAdapter.OnItemClick {


    @BindView(R.id.photo_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    PhotosListPresenter mPhotosListPresenter;

    @Inject
    PhotosListAdapter mPhotosListAdapter;

    @Inject
    @Named("SimpleService")
    Service mService;

    public static PhotosListFragment create(String category) {
        PhotosListFragment photosListFragment = new PhotosListFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(Constants.PHOTO_CATEGORY, category);
        photosListFragment.setArguments(bundle);
        return photosListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_list_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mPhotosListPresenter.onCreate();
        initSwipeRefreshLayout();
        mPhotosListPresenter.initAdapter(mRecyclerView, mLinearLayoutManager);
        mPhotosListPresenter.initSwipeRefreshLayout(mSwipeRefreshLayout);
        return rootView;
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    @Override
    protected void setupFragmentComponent() {
        Application.get(getActivity()).getAppComponent().plus(new PhotosListModule(this, this)).inject(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPhotosListPresenter.fetchPhotos(getArguments().getString(Constants.PHOTO_CATEGORY), mService);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showProgress(boolean status) {
        if (status) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPopupMessage(String title, String message) {

    }

    @Override
    public void showNoInternetDialog() {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showEmptyView() {

    }

    public void getPhotosOfSelectedCategory(String category) {
        mPhotosListPresenter.resetPagination();
        mPhotosListPresenter.fetchPhotos(category, mService);
    }


    @Override
    public void onSelectedItem(Photo photo) {
        loadPhotoDetailFragment(photo);
    }

    private void loadPhotoDetailFragment(Photo photo) {
        mFragmentInteractionListener.loadFragment(R.id.sub_content_frame,
                PhotoDetailFragment.create(photo.getId()),
                PhotoDetailFragment.TAG,
                0, 0,
                FragmentTransactionType.ADD_TO_BACK_STACK_AND_ADD);
    }
}
