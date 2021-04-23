package com.example.artist.adapter.viewholder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.Dal1ImageAlbumCardviewBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;
import com.example.artist.model.BaseModelList;

public class ArtistCardViewVH extends  RecyclerView.ViewHolder{
    public Dal1ImageAlbumCardviewBinding imageBinding;



    public ArtistCardViewVH(@NonNull Dal1ImageAlbumCardviewBinding binding){
        super(binding.getRoot());
        this.imageBinding = binding;
    }

    public void bindView(ArtistCardViewVH holder, BaseModelList model){
        ArtistData selectedItem = (ArtistData) model;
        Context context = imageBinding.getRoot().getContext();

        if (selectedItem.images != null && selectedItem.images.size() > 0) {
            String imageUrl = "https://file.thedarkmetal.com/" + selectedItem.images.get(0);
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageBinding.albumImage);
        }

        holder.imageBinding.name.setText(selectedItem.name);
        if (selectedItem.country != null) {
            holder.imageBinding.artist.setText(selectedItem.country.name);
        }
        holder.imageBinding.released.setText(String.valueOf(selectedItem.genres));

    }
}

