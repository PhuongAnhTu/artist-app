package com.example.artist.playAudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.ArtistApp;
//import com.example.artist.adapter.thumbAdapter.DetailArtistAdapter;
//import com.example.artist.adapter.thumbAdapter.DetailScreenAdapter;
import com.example.artist.adapter.viewholder.SongViewHolder;
//import com.example.artist.detailScreen.NewDetailAlbumFragment;
//import com.example.artist.detailScreen.NewDetailArtistFragment;
import com.example.artist.model.SongData;
import com.example.artist.util.LogUtil;
import com.example.artist.util.MyUtil;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;

import java.util.List;

public class BackgroundSoundService extends Service {
    public interface ServiceListener {
        void onPlayStateChange(int state);
    }

    private ServiceListener serviceListener;

    public void setServiceListener(ServiceListener listener) {
        this.serviceListener = listener;
    }

    public class MyBinder extends Binder {
        public BackgroundSoundService getService() {
            return BackgroundSoundService.this;
        }
    }

    protected SimpleExoPlayer player;

    private IBinder binder = new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("onCreate");

        player = new SimpleExoPlayer.Builder(ArtistApp.getAppContext()).build();
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (serviceListener != null) {
                    serviceListener.onPlayStateChange(state);
                }
//                setPlayer(player);
//                setCurrentPlayingPosition(currentPlayingPosition);
//                listener.onPlayIcon(currentPlayingPosition);
//                if (player.getPlaybackState() == ExoPlayer.STATE_ENDED) {
//                    int nextPosition;
//                    if (currentPlayingPosition == listSong.size() - 1) {
//                        nextPosition = 0;
//                    } else {
//                        nextPosition = currentPlayingPosition + 1;
//                    }
//                    playSongData(nextPosition);
//                }
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            LogUtil.d("onStartCommand no data");
            return super.onStartCommand(intent, flags, startId);
        }
        String command = bundle.getString("command");
        LogUtil.d("onStartCommand " + command);
        switch (command) {
            case "play":
                break;
            case "pause":
                break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }

    public void playSongData(int songPosition) {
//        SongData songData = listSong.get(songPosition);
//        if (songPosition == currentPlayingPosition) {
//            player.setPlayWhenReady(!player.getPlayWhenReady());
//        } else {
//            currentPlayingPosition = songPosition;
//            HttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSourceFactory();
//            DataSource.Factory dataSourceFactory = () -> {
//                HttpDataSource dataSource = httpDataSourceFactory.createDataSource();
//                dataSource.setRequestProperty("Origin", "https://thedarkmetal.com");
//                return dataSource;
//            };
//            String url = MyUtil.getStreamingUrl(songData._id);
//            Uri uri = Uri.parse(url);
//            MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//
//            player.prepare(mediaSource);
//            player.setPlayWhenReady(true);
//        }
    }
}

