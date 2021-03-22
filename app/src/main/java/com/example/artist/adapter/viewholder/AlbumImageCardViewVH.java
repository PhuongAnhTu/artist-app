package com.example.artist.adapter.viewholder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.Dal1ImageCardviewBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;

public class AlbumImageCardViewVH extends  RecyclerView.ViewHolder{
    public Dal1ImageCardviewBinding imageBinding;



    public AlbumImageCardViewVH(@NonNull Dal1ImageCardviewBinding binding){
        super(binding.getRoot());
        this.imageBinding = binding;
    }

    public void bindView(AlbumImageCardViewVH holder, AlbumData model){
        AlbumData selectedAlbumItem = (AlbumData) model;
        Context context = imageBinding.getRoot().getContext();

        if (selectedAlbumItem.images != null && selectedAlbumItem.images.size() > 0) {
            String imageUrl = "https://file.thedarkmetal.com/" + selectedAlbumItem.images.get(0);
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageBinding.albumImage);
        }

        holder.imageBinding.name.setText(selectedAlbumItem.name);
        holder.imageBinding.artist.setText(selectedAlbumItem.artist.name);
        holder.imageBinding.released.setText(String.valueOf(selectedAlbumItem.released));

    }
}
