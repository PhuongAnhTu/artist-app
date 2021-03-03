package com.example.artist.adapter.viewholder;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.ImageThumbnailBinding;

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

