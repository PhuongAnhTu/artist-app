package com.example.artist.detailScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.adapter.thumbAdapter.DetailScreenAdapter;
import com.example.artist.adapter.viewholder.SimilarViewHolder;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.model.AlbumData;
import com.example.artist.model.SongData;
import com.example.artist.playAudio.BackgroundSoundService;
import com.example.artist.util.LogUtil;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Util;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailAlbumFragment extends FragmentBase {

    protected DetailBaseLayoutBinding binding;
    private MainActivity mainActivity;
    public AlbumData selectedAlbumItem;
    protected List<SongData> listSong;
    protected List<AlbumData> listSimilar;
    protected AlbumData selectedSimilarAlbum;
    protected SimpleExoPlayer player;
    protected DetailScreenAdapter adapter;
    private ArrayList<MediaSource> mediaSourcesList = new ArrayList<>();
    private BackgroundSoundService service ;
    private static NewDetailAlbumFragment instance;

    private int currentPlayingPosition = -1;

    private SongViewHolder.Listener songListener = new SongViewHolder.Listener() {
        @Override
        public void onBtnPlay(int songPosition) {
            int prevPlayingPosition = currentPlayingPosition;
//            playSongData(songPosition);
            adapter.notifyItemChanged(adapter.getRecyclerViewPositionFromSongPosition(songPosition));
            if (prevPlayingPosition != songPosition) {
                adapter.notifyItemChanged(adapter.getRecyclerViewPositionFromSongPosition(prevPlayingPosition));
            }
        }

        @Override
        public int getCurrentPlayingPosition() {
            service.getCurrentPlayingPosition(currentPlayingPosition);
            return currentPlayingPosition;
        }
    };

    private SimilarViewHolder.Listener similarListener = new SimilarViewHolder.Listener() {
        @Override
        public void onSimilarAlbum(int similarPosition) {
            selectedSimilarAlbum = listSimilar.get(similarPosition);
            mainActivity.showDetailOneAlbum(selectedSimilarAlbum);
        }
    };

    public void setPlayer(SimpleExoPlayer player) {
        this.player = player;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }

        service =  new BackgroundSoundService();
        getActivity().startService(new Intent(getActivity(),BackgroundSoundService.class));
//        player = new SimpleExoPlayer.Builder(getContext()).build();
//        player.addListener(new Player.EventListener() {
//            @Override
//            public void onPlaybackStateChanged(int state) {
//                LogUtil.d("xxx onPlaybackStateChanged " + state + ", currentPlayingPosition: " + currentPlayingPosition);
//                adapter.notifyItemChanged(adapter.getRecyclerViewPositionFromSongPosition(currentPlayingPosition));
//
//
//                if (player.getPlaybackState() == ExoPlayer.STATE_ENDED) {
//                    int nextPosition;
//                    if (currentPlayingPosition == listSong.size() - 1) {
//                        nextPosition = 0;
//                    } else {
//                        nextPosition = currentPlayingPosition + 1;
//                    }
//                    playSongData(nextPosition);
//                }
//            }
//        });

    }


    public static NewDetailAlbumFragment newInstance(AlbumData album) {
        NewDetailAlbumFragment fragment = new NewDetailAlbumFragment();
        Bundle b = new Bundle();
        b.putSerializable("album", album);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedAlbumItem = (AlbumData) bundle.getSerializable("album");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_base_layout, container, false);
        adapter = new DetailScreenAdapter(songListener, player, similarListener);
        service.setAdapter(adapter);
        adapter.setAlbumData(selectedAlbumItem);
        init();
        instance = this;
        return binding.getRoot();
    }

    public void init() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        loadSongList();
        loadSimilarList();
        binding.container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                refresh();
                loadSimilarList();
                loadSongList();
            }
        });
    }

    public static NewDetailAlbumFragment getInstance() {
        return instance;
    }

//    protected void playSongData(int songPosition) {
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

//    }

    protected void refresh() {
        binding.container.setRefreshing(false);
    }

    @Override
    public String getHeaderTitle() {
        return "Detail Album";
    }

    public void loadSongList() {
        startLoading();
        APIService api = RetrofitClient.createClient();
        api.loadSongOfAlbum("Bearer" + mainActivity.getUserToken(), selectedAlbumItem.code).enqueue(new Callback<APIResponse<AlbumDetailResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumDetailResponse>> call, Response<APIResponse<AlbumDetailResponse>> response) {
                APIResponse<AlbumDetailResponse> songList = response.body();
                listSong = songList.data.songs;
                service.setListSong(listSong);
                adapter.setListSongs(songList.data.songs);
                stopLoading();
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumDetailResponse>> call, Throwable t) {
                LogUtil.logException("onFailure", t);
                stopLoading();
            }
        });
    }

    public void loadSimilarList() {
        startLoading();
        APIService api = RetrofitClient.createClient();
        api.loadSimilarAlbum("Bearer" + mainActivity.getUserToken(), selectedAlbumItem.code).enqueue(new Callback<APIResponse<AlbumListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumListResponse>> call, Response<APIResponse<AlbumListResponse>> response) {
                APIResponse<AlbumListResponse> similar = response.body();
                listSimilar = similar.data.list_data;
                adapter.setSimilarAlbum(listSimilar);
                stopLoading();
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumListResponse>> call, Throwable t) {
                LogUtil.logException("onFailure", t);
                stopLoading();
            }
        });
    }

    public void startLoading() {
        isLoading = true;
        if (mLoadedItem == 0) {
            binding.loading.setVisibility(View.VISIBLE);
        }
    }

    protected void stopLoading() {
        isLoading = false;
        binding.loading.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            init();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            init();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
//        if (adapter.songViewHolder != null) {
//            adapter.songViewHolder.songBinding.playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        }
    }

    private void releasePlayer() {
        if (player != null) {
//            playWhenReady = player.getPlayWhenReady();
//            playbackPosition = player.getCurrentPosition();
//            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (player == null) {
//            startPlayer();
//            playerNotificationManager = new PlayerNotificationManager(
//                    this, CHANNEL_ID, NOTIFICATION_ID,
//                    createMediaDescriptionAdapter(),
//                    new PlayerNotificationManager.NotificationListener() {
//                        @Override
//                        public void onNotificationPosted(int notificationId, Notification notification, boolean ongoing) {
//                            startForeground(notificationId, notification);
//                        }
//                        @Override
//                        public void onNotificationCancelled(int notificationId, boolean dismissedByUser) {
//                            if (dismissedByUser) {
//                                // Do what the app wants to do when dismissed by the user,
//                                // like calling stopForeground(true); or stopSelf();
//                            }
//                        }
//                    });
//            playerNotificationManager.setPlayer(player);
//        }
//        return START_STICKY;
//    }
}
