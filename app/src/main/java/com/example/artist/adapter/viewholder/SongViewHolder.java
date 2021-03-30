package com.example.artist.adapter.viewholder;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;

public class SongViewHolder extends  RecyclerView.ViewHolder{
    public Dal3ItemSongBinding songBinding;
    private Listener listener;


    public interface Listener {
        void onBtnPlay(SongData songData);
    }

    public SongViewHolder(@NonNull Dal3ItemSongBinding binding, @NonNull Listener listener){
        super(binding.getRoot());
        this.songBinding = binding;
        this.listener = listener;
    }

    public void bindView(SongViewHolder holder, BaseModelList model){
        SongData songData = (SongData) model;
        Log.e("songData.songs.num", String.valueOf(songData));

        String numberText = songData.num + ". " ;
        holder.songBinding.number.setText(numberText);
        holder.songBinding.songName.setText(songData.title);
        holder.songBinding.duration.setText(convertDuration(songData.duration));
        holder.songBinding.playBtn.setOnClickListener(v -> {
            listener.onBtnPlay(songData);
        });
    }

    public String convertDuration (int duration){
        long min = (duration / 1000) / 60;
        int second = ((duration / 1000) % 60);
        return min + ":" + second;
    }
}
