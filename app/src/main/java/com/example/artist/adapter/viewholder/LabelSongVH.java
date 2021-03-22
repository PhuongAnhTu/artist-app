package com.example.artist.adapter.viewholder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artist.databinding.Dal2LabelListSongBinding;
import com.example.artist.model.BaseModelList;

public class LabelSongVH extends  RecyclerView.ViewHolder{
    public Dal2LabelListSongBinding labelBinding;

    public LabelSongVH(@NonNull Dal2LabelListSongBinding binding){
        super(binding.getRoot());
        this.labelBinding = binding;
    }

    public void bindView(LabelSongVH holder){
        holder.labelBinding.getRoot();
    }
}