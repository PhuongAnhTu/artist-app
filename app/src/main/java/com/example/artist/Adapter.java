package com.example.artist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.ImageThumbnailBinding;

import java.io.File;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    ImageThumbnailBinding binding;

    public Adapter(Context context) {
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ImageThumbnailBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull ImageThumbnailBinding binding) {
            super(binding.getRoot());
        }

        public void bindView(Context context, String image) {
            Glide.with(context)
                    .load(R.drawable.avatar_default)
                    .into(binding.myImage);

        }
    }
}
