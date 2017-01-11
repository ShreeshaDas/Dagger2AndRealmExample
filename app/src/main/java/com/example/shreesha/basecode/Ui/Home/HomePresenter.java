package com.example.shreesha.basecode.Ui.Home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by shreesha on 30/12/16.
 */

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View view) {
        mHomeView = view;
    }

    private String[] getLeftDrawerItem() {
        String[] title = {
                "Uncategorized",
                "Abstract",
                "Animals",
                "Black and White",
                "City and Architecture",
                "Commercial",
                "Concert",
                "Family",
                "Film",
                "Fine Art",
                "Food",
                "Journalism",
                "Landscapes",
                "Macro",
                "Nature",
                "People",
                "Performing Arts",
                "Sport",
                "Still Life",
                "Street",
                "Transportation",
                "Travel",
                "Underwater",
                "Urban Exploration",
                "Wedding"
        };
        return title;
    }

    public void setLeftDrawerAdapter(ListView leftDrawerListView) {
        // Set the adapter for the list view
        leftDrawerListView.setAdapter(new ArrayAdapter<String>(mHomeView.getContext(),
                android.R.layout.simple_list_item_1, getLeftDrawerItem()));
        leftDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        selectItem(2);
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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        // Highlight the selected item, update the title, and close the drawer
        mHomeView.updateLeftDrawer(position);
        mHomeView.loadPhotoListFragment(getLeftDrawerItem()[position]);
        mHomeView.setActionBarTitle(getLeftDrawerItem()[position]);
        mHomeView.closeLeftDrawer();
    }

}
