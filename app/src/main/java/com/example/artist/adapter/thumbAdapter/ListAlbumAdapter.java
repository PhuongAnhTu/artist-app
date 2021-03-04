package com.example.artist.adapter.thumbAdapter;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.artist.adapter.baseadapter.BaseItemAdapter;
import com.example.artist.adapter.viewholder.DetailViewHolder;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;

public class ListAlbumAdapter extends BaseItemAdapter {
    @Override
    protected void bindData(DetailViewHolder holder, BaseModelList model) {
        AlbumData albumData = (AlbumData) model;
        Context context = holder.itemBinding.getRoot().getContext();
        if (albumData.images != null && albumData.images.size() > 0) {
            String imageUrl = holder.getImageUrl(albumData.images.get(0));
            Log.d("xxx", "imageUrl: " + imageUrl);
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.itemBinding.image);
        }
        holder.itemBinding.name.setText(albumData.name);
        holder.itemBinding.responseText.setText(albumData.genres);
        holder.itemBinding.responseText3.setText(albumData.artist.name);
        holder.itemBinding.text3.setText("Artist:");
    }
}
