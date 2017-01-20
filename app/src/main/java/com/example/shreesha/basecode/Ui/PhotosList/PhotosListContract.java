package com.example.shreesha.basecode.Ui.PhotosList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shreesha.basecode.Network.NetworkUtils;
import com.example.shreesha.basecode.Network.Service;
import com.example.shreesha.basecode.Ui.Common.BaseContract;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotosListContract {
    interface View extends BaseContract.View {
        void showEmptyView();
    }

    interface Presenter extends BaseContract.Presenter {

        void initAdapter(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager);

        void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout);

        void fetchPhotos(String category, Service service);

        void resetPagination();
    }
}
