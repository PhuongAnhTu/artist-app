package com.example.artist.adapter.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.Dal2LabelListSongBinding;
import com.example.artist.databinding.Dal4LabelSimilarBinding;
import com.example.artist.model.BaseModelList;

public class LabelSimilarVH extends  RecyclerView.ViewHolder{
    public Dal4LabelSimilarBinding labelBinding;

    public LabelSimilarVH(@NonNull Dal4LabelSimilarBinding binding){
        super(binding.getRoot());
        this.labelBinding = binding;
    }

    public void bindView(LabelSimilarVH holder){
        holder.labelBinding.getRoot();
    }
}
