package com.example.artist.detailScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.model.AlbumData;
import com.example.artist.model.SongData;
import com.example.artist.util.LogUtil;
import com.example.artist.util.MyUtil;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailAlbumFragment extends FragmentBase implements View.OnClickListener {

    protected DetailBaseLayoutBinding binding;
    private MainActivity mainActivity;
    public AlbumData selectedAlbumItem;
    protected SongData selectedSong;
    protected List<SongData> listSong;
    protected SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    private SongViewHolder.Listener songListener = new SongViewHolder.Listener() {
        @Override
        public void onBtnPlay(SongData songData) {
            setClickListenerPlayer(songData);
        }
    };
    protected DetailScreenAdapter adapter = new DetailScreenAdapter(songListener);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach (@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            this.mainActivity = (MainActivity)context;
        }
    }

    public static NewDetailAlbumFragment newInstance(AlbumData album) {
        NewDetailAlbumFragment fragment = new NewDetailAlbumFragment();
        Bundle b = new Bundle();
        b.putSerializable("album", album);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.playBtn) {
//            setClickListenerPlayer();
//        }
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null){
            selectedAlbumItem = (AlbumData) bundle.getSerializable("album");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_base_layout, container, false);
        adapter.setAlbumData(selectedAlbumItem);
        init();
        return binding.getRoot();
    }

    public void init (){
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

    protected void setClickListenerPlayer(SongData songData){

        if (player == null) {
            player = new SimpleExoPlayer.Builder(getContext()).build();
        }

        HttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSourceFactory();
//        new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true);

        DataSource.Factory dataSourceFactory = () -> {
            HttpDataSource dataSource = httpDataSourceFactory.createDataSource();
            // Set a custom authentication request header.
            dataSource.setRequestProperty("Origin", "https://thedarkmetal.com");
            return dataSource;
        };

        //DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, userAgent);
        String url = MyUtil.getStreamingUrl(songData._id);
        Uri uri = Uri.parse(url);
       // MediaItem mediaItem = MediaItem.fromUri(url);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        //player.setMediaItem(mediaItem);
        player.prepare(mediaSource);



        adapter.songViewHolder.songBinding.playerView.setPlayer(player);

        //player.setMediaItem(mediaItem);

        player.setPlayWhenReady(playWhenReady);
        //player.seekTo(currentWindow, playbackPosition);
       // player.prepare();
    }

    protected void refresh(){
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

    public void loadSimilarList(){
        startLoading();
        APIService api = RetrofitClient.createClient();
        api.loadSimilarAlbum("Bearer" + mainActivity.getUserToken(), selectedAlbumItem.code).enqueue(new Callback<APIResponse<AlbumListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumListResponse>> call, Response<APIResponse<AlbumListResponse>> response) {
                APIResponse<AlbumListResponse> similar = response.body();
                adapter.setSimilarAlbum(similar.data.list_data);
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
        if( adapter.songViewHolder!=null) {
            adapter.songViewHolder.songBinding.playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }
}
