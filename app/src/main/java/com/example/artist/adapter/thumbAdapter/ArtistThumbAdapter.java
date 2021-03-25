package com.example.artist.adapter.thumbAdapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.artist.adapter.baseadapter.BaseThumbAdapter;
import com.example.artist.adapter.viewholder.ThumbnailViewHolder;
import com.example.artist.model.ArtistData;
import com.example.artist.model.BaseModelList;

public class ArtistThumbAdapter extends BaseThumbAdapter {

    @Override
    protected void bindData (ThumbnailViewHolder holder, BaseModelList model){
        ArtistData artistData = (ArtistData) model;
        Context context = holder.thumbnailBinding.getRoot().getContext();
             if (artistData.images != null && artistData.images.size() > 0) {
                String imageUrl = holder.getImageUrl(artistData.images.get(0));
                Glide.with(context)
                     .load(imageUrl)
                     .into(holder.thumbnailBinding.myImage);
             }

        holder.thumbnailBinding.name.setText(artistData.name);
        if (artistData.country != null) {
             holder.thumbnailBinding.text3.setText(artistData.country.name);
        }
        holder.thumbnailBinding.text2.setText(artistData.genres);
    }
}



