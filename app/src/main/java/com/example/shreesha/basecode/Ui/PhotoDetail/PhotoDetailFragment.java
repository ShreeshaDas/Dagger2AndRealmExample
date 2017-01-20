package com.example.shreesha.basecode.Ui.PhotoDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shreesha.basecode.Application;
import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.R;
import com.example.shreesha.basecode.Ui.Common.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotoDetailFragment extends BaseFragment implements PhotoDetailContract.View {

    public final static String TAG = PhotoDetailFragment.class.getSimpleName();

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.image)
    ImageView mPhotoImageView;

    @BindView(R.id.user_name)
    TextView mUserName;

    @BindView(R.id.user_country)
    TextView mUserCountry;

    @BindView(R.id.user_avatar)
    ImageView mUserAvatar;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int mPhotoId;

    @Inject
    PhotoDetailPresenter mPhotoDetailPresenter;

    public static PhotoDetailFragment create(Integer id) {
        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt(Constants.PHOTO_ID, id);
        photoDetailFragment.setArguments(bundle);
        return photoDetailFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            mPhotoId = getArguments().getInt(Constants.PHOTO_ID);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mPhotoDetailPresenter.getPhotoById(mPhotoId);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPhotoDetailPresenter.onResume();
    }

    @Override
    protected void setupFragmentComponent() {
        Application.get(getActivity()).getAppComponent().plus(new PhotoDetailModule(this)).inject(this);
    }

    @Override
    public void showProgress(boolean status) {

    }

    @Override
    public void showPopupMessage(String title, String message) {

    }

    @Override
    public void showNoInternetDialog() {

    }

    @Override
    public void setCollapsingToolbarLayoutTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void setUserDetail(String userAvatar, String userName, String userCountry) {
        if (!TextUtils.isEmpty(userAvatar)) {
            Glide.with(getActivity()).load(userAvatar).placeholder(R.drawable.userpic).centerCrop().into(mUserAvatar);
        }
        if (!TextUtils.isEmpty(userName))
            mUserName.setText(userName);
        if (!TextUtils.isEmpty(userCountry))
            mUserCountry.setText(userCountry);
    }

    @Override
    public void setPhotoDescription(String imageUrl, String description) {
        Glide.with(getActivity()).load(imageUrl).centerCrop().into(mPhotoImageView);
        mDescription.setText(description);
    }
}
