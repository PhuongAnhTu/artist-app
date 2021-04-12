package com.example.artist.adapter.viewholder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.AlbumOfArtistBinding;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;

public class AlbumOfArtistVH extends RecyclerView.ViewHolder {
    public AlbumOfArtistBinding binding;
    private AlbumOfArtistVH.Listener listener;

    public interface Listener {
        void onAlbum(int songPosition);
    }


    public AlbumOfArtistVH(@NonNull AlbumOfArtistBinding binding,  @NonNull AlbumOfArtistVH.Listener listener){
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bindView(BaseModelList model, int albumPosition){
        AlbumData album = (AlbumData) model;

        binding.releasedYear.setText(album.year);

        binding.albumName.setText(album.name);

        Context context = binding.getRoot().getContext();
        if (album.images != null && album.images.size() > 0) {
            String imageUrl = getImageUrl(album.images.get(0));
            Glide.with(context)
                    .load(imageUrl)
                    .into(binding.albumImage);
        }
        binding.albumImage.setOnClickListener(v -> listener.onAlbum(albumPosition));
    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}
