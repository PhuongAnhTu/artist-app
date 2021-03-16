package com.example.artist.adapter.viewholder;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.OneSongPlayBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;

public class SongViewHolder extends  RecyclerView.ViewHolder{
    public OneSongPlayBinding songBinding;

    public SongViewHolder(@NonNull OneSongPlayBinding binding){
        super(binding.getRoot());
        this.songBinding = binding;
        }

    public void bindView(SongViewHolder holder, BaseModelList model){
            SongData songData = (SongData) model;
            holder.songBinding.songName.setText(songData.title);
            holder.songBinding.number.setText(String.valueOf(songData.num));
            holder.songBinding.duration.setText(String.valueOf(songData.duration));
        }
    }
