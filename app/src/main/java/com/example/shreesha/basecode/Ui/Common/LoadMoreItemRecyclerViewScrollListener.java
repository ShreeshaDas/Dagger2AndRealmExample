/*
 * *
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *
 *  * @File: LoadMoreItemRecyclerViewScrollListener.java
 *  * @Project: Devote
 *  * @Abstract:
 *  * @Copyright: Copyright © 2015, Saregama India Ltd. All Rights Reserved
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *  * <p/>
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 */

package com.example.shreesha.basecode.Ui.Common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class LoadMoreItemRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = LoadMoreItemRecyclerViewScrollListener.class.getSimpleName();

    private int mPreviousTotal = 0;
    private boolean mLoading = true;
    private int mVisibleThreshold = 10;
    private int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;
    private int current_page = 0;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private OnPagination mOnPagination;

    public interface OnPagination {
        void onLoadMore(int current_page);
    }

    public LoadMoreItemRecyclerViewScrollListener(LinearLayoutManager layoutManager, OnPagination onPagination) {
        this.mLinearLayoutManager = layoutManager;
        this.mOnPagination = onPagination;
    }

    public LoadMoreItemRecyclerViewScrollListener(GridLayoutManager layoutManager, OnPagination onPagination) {
        this.mGridLayoutManager = layoutManager;
        this.mOnPagination = onPagination;
    }

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * <p/>
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     *
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx           The amount of horizontal scroll.
     * @param dy           The amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mVisibleItemCount = recyclerView.getChildCount();
        if (mLinearLayoutManager != null) {
            mTotalItemCount = mLinearLayoutManager.getItemCount();
            mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        }

        if (mGridLayoutManager != null) {
            mTotalItemCount = mGridLayoutManager.getItemCount();
            mFirstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
        }
        if (mLoading) {
            if (mTotalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = mTotalItemCount;
            }
        }
        if (!mLoading
                && (mTotalItemCount - mVisibleItemCount) <= (mFirstVisibleItem + mVisibleThreshold) && mFirstVisibleItem > 0) {
            current_page++;
            mOnPagination.onLoadMore(current_page);
            mLoading = true;
        }
    }

    //public abstract void onLoadMore(int current_page);

    public void reset() {
        mPreviousTotal = 0;
        mLoading = true;
        mVisibleThreshold = 10;
        mFirstVisibleItem = 0;
        mVisibleItemCount = 0;
        mTotalItemCount = 0;
        current_page = 0;
    }
}
