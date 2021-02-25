package com.example.artist.baseadapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.model.BaseModelList;

import java.util.List;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder {

    public ImageThumbnailBinding binding;
    public ThumbnailViewHolder(@NonNull ImageThumbnailBinding binding) {
        super(binding.getRoot());
    }

//        public void bindViewArtist(List<? extends BaseModelList> listData){
//            Context context = binding.getRoot().getContext();
////            if (listData.images != null && listData.images.size() > 0) {
////                String imageUrl = getImageUrl(listData.images.get(0));
////                Log.d("xxx", "imageUrl: " + imageUrl);
////                Glide.with(context)
////                        .load(imageUrl)
////                        .into(binding.myImage);
////            }
////            binding.name.setText(listData.name);
////            binding.text2.setText(listData.genres);
////            binding.text3.setText(listData.country.name);
//
//        }
//
//        public void bindViewAlbum() {
//            Context context = binding.getRoot().getContext();
//            if (albumData.images != null && albumData.images.size() > 0) {
//                String imageUrl = getImageUrl(albumData.images.get(0));
//                Log.d("xxx", "imageUrl: " + imageUrl);
//                Glide.with(context)
//                        .load(imageUrl)
//                        .into(binding.myImage);
//            }
//            binding.name.setText(albumData.name);
//            binding.text2.setText(albumData.genres);
//            binding.text3.setText(albumData.artistData.name);
//        }



    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}

