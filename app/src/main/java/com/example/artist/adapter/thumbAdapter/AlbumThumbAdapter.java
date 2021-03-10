package com.example.artist.adapter.thumbAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.artist.R;
import com.example.artist.adapter.baseadapter.BaseThumbAdapter;
import com.example.artist.adapter.viewholder.ThumbnailViewHolder;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;

public class AlbumThumbAdapter extends BaseThumbAdapter {

    @Override
    protected void bindData(ThumbnailViewHolder holder, BaseModelList model) {
        AlbumData albumData = (AlbumData) model;
        Context context = holder.thumbnailBinding.getRoot().getContext();
            if (albumData.images != null && albumData.images.size() > 0) {
                String imageUrl = holder.getImageUrl(albumData.images.get(0));
                Glide.with(context)
                     .load(imageUrl)
                     .into(holder.thumbnailBinding.myImage);
            }

        holder.thumbnailBinding.name.setText(albumData.name);
        holder.thumbnailBinding.text2.setText(albumData.genres);
        holder.thumbnailBinding.text3.setText(albumData.artist.name);
    }
}
