package com.example.artist.adapter.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.R;
import com.example.artist.databinding.Dal2LabelTextBinding;

public class LabelVH extends  RecyclerView.ViewHolder{
    public Dal2LabelTextBinding labelBinding;
    public final int SONG_LABEL = 0;
    public final int SIMILAR_LABEL = 1;
    public final int ALBUM_LABEL = 2;

    public LabelVH(@NonNull Dal2LabelTextBinding binding){
        super(binding.getRoot());
        this.labelBinding = binding;
    }

    public void bindView(LabelVH holder, int labelType){
        holder.labelBinding.getRoot();
        if (labelType == SONG_LABEL) {
            holder.labelBinding.textLabel.setText(R.string.album_songs);
        } else if (labelType == SIMILAR_LABEL){
            holder.labelBinding.textLabel.setText(R.string.you_might_like);
        } else if (labelType == ALBUM_LABEL) {
            holder.labelBinding.textLabel.setText(R.string.album);
        }
    }
}