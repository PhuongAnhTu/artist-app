package com.example.artist.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.LoadingItemBinding;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public LoadingItemBinding binding;

    public LoadingViewHolder(@NonNull LoadingItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }
}
