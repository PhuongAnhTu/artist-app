package com.example.artist.adapter.viewholder;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.AlbumThumbAdapter;
import com.example.artist.adapter.ArtistThumbAdapter;
import com.example.artist.adapter.baseadapter.BaseThumbAdapter;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.model.ArtistData;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder  {



    public ThumbnailViewHolder(@NonNull ImageThumbnailBinding binding) {

        super(binding.getRoot());
    }


    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }



//    public interface ClickListener {
//        void onItemClick(int position, View v);
//    }


}

