package com.example.artist.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.model.ArtistData;
import com.example.artist.databinding.ImageThumbnailBinding;

import java.util.ArrayList;
import java.util.List;

public class ArtistThumbAdapter extends RecyclerView.Adapter<ArtistThumbAdapter.MyViewHolder> {

    ImageThumbnailBinding binding;
    private List<ArtistData> artistDataList = new ArrayList<>();

    public ArtistThumbAdapter() {
    }

    public void addData(List<ArtistData> list) {
        artistDataList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArtistThumbAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ImageThumbnailBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ArtistThumbAdapter.MyViewHolder holder, int position) {
        holder.bindView(artistDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return artistDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull ImageThumbnailBinding binding) {
            super(binding.getRoot());
        }

            public void bindView(ArtistData artistData){
                Context context = binding.getRoot().getContext();
                if (artistData.images != null && artistData.images.size() > 0) {
                    String imageUrl = getImageUrl(artistData.images.get(0));
                    Log.d("xxx", "imageUrl: " + imageUrl);
                    Glide.with(context)
                            .load(imageUrl)
                            .into(binding.myImage);
                }
                binding.name.setText(artistData.name);
                binding.text3.setText(artistData.country.name);
                binding.text2.setText(artistData.genres);

            }
        }

        public String getImageUrl(String relativeUrl) {
            return "https://file.thedarkmetal.com/" + relativeUrl;
        }
    }


