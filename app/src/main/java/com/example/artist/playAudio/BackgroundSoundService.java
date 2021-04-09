package com.example.artist.playAudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.ArtistApp;
import com.example.artist.adapter.thumbAdapter.DetailScreenAdapter;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.detailScreen.NewDetailAlbumFragment;
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
    protected SimpleExoPlayer player;
    private int currentPlayingPosition = -1;
    private DetailScreenAdapter adapter;
    protected List<SongData> listSong;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void setAdapter (DetailScreenAdapter adapter) {
        this.adapter = adapter;
    }

    public void setListSong(List<SongData> listSong) {
        this.listSong = listSong;
    }

    public void getCurrentPlayingPosition (int currentPlayingPosition) {
        this.currentPlayingPosition = currentPlayingPosition;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        player = new SimpleExoPlayer.Builder(ArtistApp.getAppContext()).build();
        NewDetailAlbumFragment.getInstance().setPlayer(player);
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
//                LogUtil.d("xxx onPlaybackStateChanged " + state + ", currentPlayingPosition: " + currentPlayingPosition);
                adapter.notifyItemChanged(adapter.getRecyclerViewPositionFromSongPosition(currentPlayingPosition));


                if (player.getPlaybackState() == ExoPlayer.STATE_ENDED) {
                    int nextPosition;
                    if (currentPlayingPosition == listSong.size() - 1) {
                        nextPosition = 0;
                    } else {
                        nextPosition = currentPlayingPosition + 1;
                    }
                    playSongData(nextPosition);
                }
            }
        });
    }

    protected void playSongData(int songPosition) {
        SongData songData = listSong.get(songPosition);
        if (songPosition == currentPlayingPosition) {
            player.setPlayWhenReady(!player.getPlayWhenReady());
        } else {
            currentPlayingPosition = songPosition;
            HttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSourceFactory();
            DataSource.Factory dataSourceFactory = () -> {
                HttpDataSource dataSource = httpDataSourceFactory.createDataSource();
                dataSource.setRequestProperty("Origin", "https://thedarkmetal.com");
                return dataSource;
            };
            String url = MyUtil.getStreamingUrl(songData._id);
            Uri uri = Uri.parse(url);
            MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }
    }

//    public int onStartCommand(Intent intent, int flags, int startId) {
//        player.start();
//        return Service.START_STICKY;
//    }
//
//    public void onStart(Intent intent, int startId) {
//        // TO DO
//    }
//    public IBinder onUnBind(Intent arg0) {
//        // TO DO Auto-generated method
//        return null;
//    }
//
//    public void onStop() {
//
//    }
//    public void onPause() {
//
//    }
//    @Override
//    public void onDestroy() {
//        mediaPlayer.stop();
//        mediaPlayer.release();
//    }
//
//    @Override
//    public void onLowMemory() {
//
//    }
}

