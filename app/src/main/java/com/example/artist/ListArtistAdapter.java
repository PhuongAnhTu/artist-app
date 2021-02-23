package com.example.artist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.homeScreen.ArtistListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArtistAdapter extends RecyclerView.Adapter<ListArtistAdapter.MyViewHolder> {

    ImageThumbnailBinding binding;
    private List<ArtistData> artistDataList = new ArrayList<>();

    public ListArtistAdapter(){
    }

    public void addData(List<ArtistData> list) {
        artistDataList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListArtistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ImageThumbnailBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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

        public void bindView(ArtistData artistData) {
            Context context = binding.getRoot().getContext();
            if (artistData.images != null && artistData.images.size() > 0) {
                String imageUrl = getImageUrl(artistData.images.get(0));
                Log.d("xxx", "imageUrl: " + imageUrl);
                Glide.with(context)
                        .load(imageUrl)
                        .into(binding.myImage);
            }
            binding.name.setText(artistData.name);
            binding.text2.setText(artistData.genres);
            //binding.country.setText(artistData.country);

        }
    }

    public String getImageUrl(String relativeUrl) {
        return "https://file.thedarkmetal.com/" + relativeUrl;
    }
}
