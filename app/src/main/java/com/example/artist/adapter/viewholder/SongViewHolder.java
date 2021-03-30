package com.example.artist.adapter.viewholder;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.R;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;
import com.google.android.exoplayer2.ExoPlayer;

public class SongViewHolder extends  RecyclerView.ViewHolder{
    public Dal3ItemSongBinding songBinding;
    private Listener listener;
    private int playingState;
    private int currentPosition;



    public interface Listener {
        void onBtnPlay(SongData songData, int position, int playerStatus);
    }

    public SongViewHolder(@NonNull Dal3ItemSongBinding binding, @NonNull Listener listener){
        super(binding.getRoot());
        this.songBinding = binding;
        this.listener = listener;
    }

    public void bindView(SongViewHolder holder, BaseModelList model, ExoPlayer player){
        SongData songData = (SongData) model;
        Log.e("songData.songs.num", String.valueOf(songData));

        String numberText = songData.num + ". " ;
        holder.songBinding.number.setText(numberText);
        holder.songBinding.songName.setText(songData.title);
        holder.songBinding.duration.setText(convertDuration(songData.duration));
        holder.songBinding.playBtn.setOnClickListener(v -> {
            listener.onBtnPlay(songData, currentPosition , playingState );



            if (holder.getAdapterPosition() != currentPosition){

                //implement change icon when change song positon

            } else {
                if (player.isPlaying()) {
                                        holder.songBinding.playBtn.setImageResource(R.drawable.pause_btn);
                } else {
                    holder.songBinding.playBtn.setImageResource(R.drawable.play_btn);
                }
            }
        });
    }



    public String convertDuration (int duration){
        long min = (duration / 1000) / 60;
        int second = ((duration / 1000) % 60);
        return min + ":" + second;
    }


}
