package com.example.artist.detailScreen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.adapter.thumbAdapter.DetailAlbumSongAdapter;
import com.example.artist.adapter.thumbAdapter.SimilarSongAdapter;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.AlbumDetailBinding;
import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.model.AlbumData;
import com.example.artist.model.SongData;
import com.example.artist.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOneAlbumFragment extends FragmentBase implements View.OnClickListener {
    private AlbumDetailBinding binding;
    private List<SongData> listSong = new ArrayList<>();
    private List<AlbumData> listSimilar = new ArrayList<>();
    private MainActivity mainActivity;
    private AlbumData selectedAlbumItem;
    private DetailAlbumSongAdapter songAdapter = new DetailAlbumSongAdapter();
    private SimilarSongAdapter similarAdapter = new SimilarSongAdapter();


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

    public static DetailOneAlbumFragment newInstance(AlbumData album) {
        DetailOneAlbumFragment fragment = new DetailOneAlbumFragment();
        Bundle b = new Bundle();
        b.putSerializable("album", album);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null){
            selectedAlbumItem = (AlbumData) bundle.getSerializable("album");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.album_detail, container, false);
        init();
        return binding.getRoot();
    }

    protected void init(){
        Context context = binding.getRoot().getContext();
        if (selectedAlbumItem.images != null && selectedAlbumItem.images.size() > 0) {
            String imageUrl = "https://file.thedarkmetal.com/" + selectedAlbumItem.images.get(0);
            Glide.with(context)
                    .load(imageUrl)
                    .into(binding.albumImage);
        }

        binding.name.setText(selectedAlbumItem.name);
        binding.artist.setText(selectedAlbumItem.artist.name);
        binding.released.setText(String.valueOf(selectedAlbumItem.released));



        binding.song.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.song.setAdapter(songAdapter);

        binding.similarSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.similarSongs.setAdapter(similarAdapter);

        loadSongList();
        loadSimilarList();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public String getHeaderTitle() {
        return "Detail Album";
    }

    public void loadSongList() {
        APIService api = RetrofitClient.createClient();
        api.loadSongOfAlbum("Bearer" + mainActivity.getUserToken(), selectedAlbumItem.code).enqueue(new Callback<APIResponse<AlbumDetailResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumDetailResponse>> call, Response<APIResponse<AlbumDetailResponse>> response) {
                APIResponse<AlbumDetailResponse> songList = response.body();
                listSong = songList.data.songs;
                songAdapter.addData(listSong);
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumDetailResponse>> call, Throwable t) {
                Log.e("TAG", "onFailure: ");
            }
        });
    }

    public void loadSimilarList(){
        APIService api = RetrofitClient.createClient();
        api.loadSimilarAlbum("Bearer" + mainActivity.getUserToken(), selectedAlbumItem.code).enqueue(new Callback<APIResponse<AlbumListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumListResponse>> call, Response<APIResponse<AlbumListResponse>> response) {
                APIResponse<AlbumListResponse> similar = response.body();
                listSimilar = similar.data.list_data;
                similarAdapter.addData(listSimilar);
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumListResponse>> call, Throwable t) {
                LogUtil.logException("onFailure", t);
            }
        });
    }
}

