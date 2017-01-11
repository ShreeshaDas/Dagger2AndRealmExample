package com.example.shreesha.basecode.Ui.Home;

import com.example.shreesha.basecode.Ui.Common.BaseContract;

/**
 * Created by shreesha on 30/12/16.
 */

public interface HomeContract {
    interface View extends BaseContract.View {
        void setActionBarTitle(String title);

        void closeLeftDrawer();

        void loadPhotoListFragment(String category);

        void updateLeftDrawer(int position);
    }

    interface Presenter extends BaseContract.Presenter {

    }

}
