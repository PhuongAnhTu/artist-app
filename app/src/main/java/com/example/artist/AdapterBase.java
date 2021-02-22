package com.example.artist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artist.databinding.ImageThumbnailBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterBase extends RecyclerView.Adapter<AdapterBase.MyViewHolder> {

    ImageThumbnailBinding binding;
    private final Context mContext;
    private List<String> mListImage = new ArrayList<>();

    public AdapterBase(Context context) {
        this.mContext = context;
    }

//    public void loadData (List<String> listImage) {
//        // call API
//    }


    @NonNull
    @Override
    public AdapterBase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ImageThumbnailBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        String image = mListImage.get(position);
//        holder.bindView(mContext, image);

        binding.myImage.setImageResource(R.drawable.avatar_default);

        binding.name.setText("Name");
        binding.text2.setText("Text2");
        binding.country.setText("Country");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull ImageThumbnailBinding binding) {
            super(binding.getRoot());
        }

//        public void bindView(Context context, String image) {
//
//            Glide.with(context)
//                    .load(R.drawable.avatar_default)
//                    .into(binding.myImage);
//
//            binding.name.setText("Call Api name");
//            binding.text2.setText("Call api text2");
//            binding.country.setText("Call api country");

//        }
    }
}
