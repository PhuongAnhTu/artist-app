package com.example.artist;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.artist.baseadapter.BaseThumbAdapter;
import com.example.artist.baseadapter.ThumbnailViewHolder;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;

import java.util.ArrayList;
import java.util.List;

public class AlbumThumbAdapter extends BaseThumbAdapter {
    public ImageThumbnailBinding binding;
    protected ThumbnailViewHolder thumbnailViewHolder;


    @Override
    protected void bindData(ThumbnailViewHolder holder, BaseModelList model) {
        AlbumData albumData = (AlbumData)model;
        //

        Context context = binding.getRoot().getContext();
            if (albumData.images != null && albumData.images.size() > 0) {
                String imageUrl = thumbnailViewHolder.getImageUrl(albumData.images.get(0));
                Log.d("xxx", "imageUrl: " + imageUrl);
                Glide.with(context)
                        .load(imageUrl)
                        .into(binding.myImage);
            }
            binding.name.setText(albumData.name);
            binding.text2.setText(albumData.genres);
            binding.text3.setText(albumData.artistData.name);
    }


}
