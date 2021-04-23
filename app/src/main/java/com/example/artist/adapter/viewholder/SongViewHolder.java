package com.example.artist.adapter.viewholder;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.R;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;
import com.example.artist.util.LogUtil;
import com.google.android.exoplayer2.ExoPlayer;

public class SongViewHolder extends  RecyclerView.ViewHolder{
    public Dal3ItemSongBinding songBinding;
    private Listener listener;

    public interface Listener {
        void onBtnPlay(int songPosition);
        int getCurrentPlayingPosition();
    }

    public SongViewHolder(@NonNull Dal3ItemSongBinding binding, @NonNull Listener listener){
        super(binding.getRoot());
        this.songBinding = binding;
        this.listener = listener;
    }

    public void bindView(BaseModelList model, int songPosition){
        SongData songData = (SongData) model;
        String numberText = songData.num + ". " ;
        songBinding.number.setText(numberText);
        songBinding.songName.setText(songData.title);
        songBinding.duration.setText(convertDuration(songData.duration));

//        if (player != null) {
//            LogUtil.d("bindView songPosition: " + songPosition + ", getPlaybackState: " + player.getPlaybackState() + ", isPlaying: " + player.isPlaying());
//            if (player.getPlaybackState() == ExoPlayer.STATE_BUFFERING && songPosition == listener.getCurrentPlayingPosition()) {
//                songBinding.loadingItem.setVisibility(View.VISIBLE);
//                songBinding.playBtn.setVisibility(View.INVISIBLE);
//            } else {
//                songBinding.loadingItem.setVisibility(View.GONE);
//                songBinding.playBtn.setVisibility(View.VISIBLE);
//                if (player != null && player.isPlaying() && songPosition == listener.getCurrentPlayingPosition()) {
//                    songBinding.playBtn.setImageResource(R.drawable.play_btn);
//                } else {
//                    songBinding.playBtn.setImageResource(R.drawable.pause_btn);
//                }
//            }
//        }
        songBinding.playBtn.setOnClickListener(v -> {
            listener.onBtnPlay(songPosition);
        });
    }

    public String convertDuration (int duration){
        long min = (duration / 1000) / 60;
        int second = ((duration / 1000) % 60);
        return min + ":" + second;
    }
}
