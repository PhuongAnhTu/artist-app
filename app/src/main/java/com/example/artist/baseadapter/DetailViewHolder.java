package com.example.artist.baseadapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;
import com.example.artist.databinding.ImageItemBinding;

public class DetailViewHolder extends RecyclerView.ViewHolder {

    public ImageItemBinding binding;

    public DetailViewHolder(@NonNull ImageItemBinding binding) {
        super(binding.getRoot());
    }

    public void bindViewArtistItem(ArtistData artistData) {
        Context context = binding.getRoot().getContext();
        if (artistData.images != null && artistData.images.size() > 0) {
            String imageUrl = getImageUrl(artistData.images.get(0));
            Log.d("xxx", "imageUrl: " + imageUrl);
            Glide.with(context)
                    .load(imageUrl)
                    .into(binding.image);
        }
        binding.name.setText(artistData.name);
        binding.responseText.setText(artistData.genres);
        binding.responseText3.setText(artistData.country.name);

    }

    public void bindViewAlbumItem(AlbumData albumData) {
        Context context = binding.getRoot().getContext();
        if (albumData.images != null && albumData.images.size() > 0) {
            String imageUrl = getImageUrl(albumData.images.get(0));
            Log.d("xxx", "imageUrl: " + imageUrl);
            Glide.with(context)
                    .load(imageUrl)
                    .into(binding.image);
        }
        binding.name.setText(albumData.name);
        binding.responseText.setText(albumData.genres);
        binding.responseText3.setText(albumData.artistData.name);
        binding.text3.setText("Artist:");
    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}