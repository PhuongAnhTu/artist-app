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
import com.example.artist.adapter.thumbAdapter.DetailArtistAdapter;
import com.example.artist.adapter.viewholder.AlbumOfArtistVH;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;
import com.example.artist.model.SongData;
import com.example.artist.playAudio.BackgroundSoundService;
import com.example.artist.util.LogUtil;
import com.google.android.exoplayer2.util.Util;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailArtistFragment extends FragmentBase {

    protected DetailBaseLayoutBinding binding;
    private MainActivity mainActivity;
    public AlbumData selectedAlbumItem;
    public ArtistData selectedArtist;
    protected List<SongData> listSong;
    protected List<AlbumData> listAlbumOfArtist;
    protected DetailArtistAdapter adapter;

    private int currentPlayingPosition = -2;

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
            return currentPlayingPosition;
        }
    };

    private AlbumOfArtistVH.Listener albumOfArtistListener = new AlbumOfArtistVH.Listener() {
        @Override
        public void onAlbum(int songPosition) {

            selectedAlbumItem = listAlbumOfArtist.get(songPosition);
            mainActivity.showDetailOneAlbum(selectedAlbumItem);
        }
    };



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
    }


    public static NewDetailArtistFragment newInstance(ArtistData artistData) {
        NewDetailArtistFragment fragment = new NewDetailArtistFragment();
        Bundle b = new Bundle();
        b.putSerializable("artistData", artistData);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedArtist = (ArtistData) bundle.getSerializable("artistData");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_base_layout, container, false);
        adapter = new DetailArtistAdapter(songListener, albumOfArtistListener);
//        service.setArtistAdapter(adapter);
        adapter.setArtistData(selectedArtist);
        init();
        return binding.getRoot();
    }

    public void init() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        loadDetailArtist();
        binding.container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                refresh();
                loadDetailArtist();
            }
        });
    }

    protected void refresh() {
        binding.container.setRefreshing(false);
    }

    @Override
    public String getHeaderTitle() {
        return "Detail Artist";
    }

    public void loadDetailArtist() {
        startLoading();
        APIService api = RetrofitClient.createClient();
        api.loadDetailArtist("Bearer" + mainActivity.getUserToken(), selectedArtist.code, "all" ,0, 10).enqueue(new Callback<APIResponse<ArtistDetailResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<ArtistDetailResponse>> call, Response<APIResponse<ArtistDetailResponse>> response) {
                APIResponse<ArtistDetailResponse> artistDetail = response.body();
                listSong = artistDetail.data.songs.list_data;
//                service.setListSong(playerListener, listSong);
                adapter.setListSongs(listSong);
                adapter.setAlbumOfArtist(artistDetail.data.albums);
                stopLoading();
            }

            @Override
            public void onFailure(Call<APIResponse<ArtistDetailResponse>> call, Throwable t) {
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



}
