package com.example.shreesha.basecode.Ui.PhotoDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shreesha.basecode.Internal.Constants;
import com.example.shreesha.basecode.R;
import com.example.shreesha.basecode.Ui.Common.BaseFragment;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotoDetailFragment extends BaseFragment {

    public final static String TAG = PhotoDetailFragment.class.getSimpleName();

    public static PhotoDetailFragment create(Integer id) {
        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt(Constants.PHOTO_ID, id);
        photoDetailFragment.setArguments(bundle);
        return photoDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail_fragment, container, false);
        return rootView;
    }

    @Override
    protected void setupFragmentComponent() {

    }
}
