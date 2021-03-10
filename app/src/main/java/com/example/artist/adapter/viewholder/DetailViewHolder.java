package com.example.artist.adapter.viewholder;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.ImageItemBinding;

public class DetailViewHolder extends RecyclerView.ViewHolder{
    public ImageItemBinding itemBinding;

    public DetailViewHolder(@NonNull ImageItemBinding binding) {
        super(binding.getRoot());
        this.itemBinding = binding;
    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}