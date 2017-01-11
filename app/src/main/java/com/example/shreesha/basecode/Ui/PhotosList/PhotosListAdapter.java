package com.example.shreesha.basecode.Ui.PhotosList;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shreesha.basecode.Models.Image;
import com.example.shreesha.basecode.Models.Photo;
import com.example.shreesha.basecode.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shreesha on 4/1/17.
 */


public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.ViewHolder> {
    private List<Photo> mPhotos;
    private Context mContext;
    private OnItemClick mOnItemClick;

    public interface OnItemClick {
        void onSelectedItem(Photo photo);
    }

    public PhotosListAdapter(Context context, List<Photo> photos, OnItemClick onItemClick) {
        this.mContext = context;
        this.mPhotos = photos;
        this.mOnItemClick = onItemClick;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        @BindView(R.id.photo)
        public ImageView mPhoto;

        @BindView(R.id.photo_name)
        public TextView mPhotoName;

        @BindView(R.id.card_view)
        public CardView mContainer;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void setData(List<Photo> data) {
        mPhotos.clear();
        mPhotos.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<Photo> data) {
        mPhotos.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mPhotos.clear();
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PhotosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Photo photo = mPhotos.get(position);
        if (photo != null) {
            holder.mPhotoName.setText(photo.getName());
            List<Image> image = photo.getImages();
            if (image.get(1) != null && !TextUtils.isEmpty(image.get(1).getUrl())) {
                Glide.with(mContext).load(image.get(1).getUrl()).centerCrop().into(holder.mPhoto);
            }
            holder.mContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClick.onSelectedItem(photo);
                }
            });
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}

