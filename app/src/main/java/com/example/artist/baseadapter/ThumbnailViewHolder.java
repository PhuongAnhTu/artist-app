package com.example.artist.baseadapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.model.BaseModelList;

import java.util.List;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder {

    public ThumbnailViewHolder(@NonNull ImageThumbnailBinding binding) {
        super(binding.getRoot());
    }


    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}

