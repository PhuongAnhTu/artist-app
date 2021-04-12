package com.example.artist.adapter.viewholder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;

public class SimilarViewHolder extends  RecyclerView.ViewHolder{
    public Dal5SimilarItemBinding similarBinding;


    public SimilarViewHolder(@NonNull Dal5SimilarItemBinding binding){
        super(binding.getRoot());
        this.similarBinding = binding;
    }

    public void bindView(BaseModelList model){
        AlbumData similar = (AlbumData) model;

        String songName = similar.name + " (" + similar.released + " " + similar.type + ")" ;
        similarBinding.songName.setText(songName);

        similarBinding.artist.setText(similar.artist.name);
        similarBinding.genres.setText(similar.genres);

        Context context = similarBinding.getRoot().getContext();
        if (similar.images != null && similar.images.size() > 0) {
            String imageUrl = getImageUrl(similar.images.get(0));
            Glide.with(context)
                    .load(imageUrl)
                    .into(similarBinding.songImage);
        }

    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}
