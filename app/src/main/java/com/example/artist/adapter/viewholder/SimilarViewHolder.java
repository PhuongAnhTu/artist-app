package com.example.artist.adapter.viewholder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.OneSongPlayBinding;
import com.example.artist.databinding.OneSongSimilarBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;

public class SimilarViewHolder extends  RecyclerView.ViewHolder{
    public OneSongSimilarBinding similarBinding;


    public SimilarViewHolder(@NonNull OneSongSimilarBinding binding){
        super(binding.getRoot());
        this.similarBinding = binding;
    }

    public void bindView(SimilarViewHolder holder, BaseModelList model){
        AlbumData similar = (AlbumData) model;

        String songName = similar.name + " (" + similar.released + " " + similar.type + ")" ;
        holder.similarBinding.songName.setText(songName);

        holder.similarBinding.artist.setText(similar.artist.name);
        holder.similarBinding.genres.setText(similar.genres);

        Context context = holder.similarBinding.getRoot().getContext();
        if (similar.images != null && similar.images.size() > 0) {
            String imageUrl = holder.getImageUrl(similar.images.get(0));
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.similarBinding.songImage);
        }

    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}
