package com.example.shreesha.basecode.Ui.PhotoDetail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.Ui.Common.BaseContract;

/**
 * Created by shreesha on 4/1/17.
 */

public class PhotoDetailContract {

    interface View extends BaseContract.View {
        void setCollapsingToolbarLayoutTitle(String title);

        void setUserDetail(String userAvatar, String userName, String userCountry);

        void setPhotoDescription(String imageUrl, String description);
    }

    interface Presenter extends BaseContract.Presenter {
        void getPhotoById(int photoId);
    }
}
