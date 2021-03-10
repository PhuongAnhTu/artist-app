package com.example.artist.adapter.viewholder;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.ImageThumbnailBinding;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder  {
    public ImageThumbnailBinding thumbnailBinding;

    public ThumbnailViewHolder(@NonNull ImageThumbnailBinding binding) {
        super(binding.getRoot());
        this.thumbnailBinding = binding;
    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}

