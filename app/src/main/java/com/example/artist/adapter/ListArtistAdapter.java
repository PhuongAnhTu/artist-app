package com.example.artist.adapter;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.artist.adapter.baseadapter.BaseItemAdapter;
import com.example.artist.adapter.viewholder.DetailViewHolder;
import com.example.artist.model.ArtistData;
import com.example.artist.model.BaseModelList;


public class ListArtistAdapter extends BaseItemAdapter {
    @Override
    protected void bindData(DetailViewHolder holder, BaseModelList model) {
        ArtistData artistData = (ArtistData) model;

        Context context = itemBinding.getRoot().getContext();
        if (artistData.images != null && artistData.images.size() > 0) {
            String imageUrl = holder.getImageUrl(artistData.images.get(0));
            Log.d("xxx", "imageUrl: " + imageUrl);
            Glide.with(context)
                    .load(imageUrl)
                    .into(itemBinding.image);
        }
        itemBinding.name.setText(artistData.name);
        itemBinding.responseText.setText(artistData.genres);
        itemBinding.responseText3.setText(artistData.country.name);

    }
}
