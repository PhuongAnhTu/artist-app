package com.example.artist.adapter;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.artist.adapter.baseadapter.BaseThumbAdapter;
import com.example.artist.adapter.viewholder.ThumbnailViewHolder;
import com.example.artist.model.ArtistData;
import com.example.artist.model.BaseModelList;

public class ArtistThumbAdapter extends BaseThumbAdapter {

    @Override
    protected void bindData (ThumbnailViewHolder holder, BaseModelList model){
        ArtistData artistData = (ArtistData) model;

        Context context = thumbnailBinding.getRoot().getContext();
                if (artistData.images != null && artistData.images.size() > 0) {
                    String imageUrl = holder.getImageUrl(artistData.images.get(0));
                    Log.d("xxx", "imageUrl: " + imageUrl);
                    Glide.with(context)
                            .load(imageUrl)
                            .into(thumbnailBinding.myImage);
                }
        thumbnailBinding.name.setText(artistData.name);
        thumbnailBinding.text3.setText(artistData.country.name);
        thumbnailBinding.text2.setText(artistData.genres);
    }
}



