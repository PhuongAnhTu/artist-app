package com.example.artist.homeScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.adapter.baseadapter.BaseThumbAdapter;
import com.example.artist.adapter.viewholder.ThumbnailViewHolder;
import com.example.artist.model.AlbumData;
import com.example.artist.adapter.ArtistThumbAdapter;
import com.example.artist.model.ArtistData;
import com.example.artist.adapter.AlbumThumbAdapter;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.HomeFragmentBinding;
import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.listAll.ArtistListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends FragmentBase implements View.OnClickListener {
    private HomeFragmentBinding homeBinding;
    public MainActivity mainActivity;

    ArtistThumbAdapter artistThumbAdapter = new ArtistThumbAdapter();
    AlbumThumbAdapter albumThumbAdapter = new AlbumThumbAdapter();



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

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);

        mainActivity.getSupportActionBar().hide();
        init();
        return homeBinding.getRoot();
    }

    private void init () {
        homeBinding.showDetailArt.setOnClickListener(this);
        homeBinding.artistRecyclerView.setOnClickListener(this);
        homeBinding.albumRecyclerView.setOnClickListener(this);
        homeBinding.showDetailAlb.setOnClickListener(this);
        createArtistRecyclerView();
        createAlbumRecyclerView();
        loadArtists();
        loadAlbums();
    }


    public void createArtistRecyclerView(){
        homeBinding.artistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        homeBinding.artistRecyclerView.setAdapter(artistThumbAdapter);
        artistThumbAdapter.setOnClick(new BaseThumbAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                mainActivity.showDetailOneArtist();
            }
        });
        homeBinding.artistRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

//    private void createRecyclerView(RecyclerView view, List<String> str){
//        view.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        view.setItemAnimator(new DefaultItemAnimator());
//        AdapterBase adapter = new AdapterBase(getContext(), str);
//        view.setAdapter(adapter);
//
//            view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                }
//            });
//
//
//    }

    public void createAlbumRecyclerView(){
        homeBinding.albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        homeBinding.albumRecyclerView.setAdapter(albumThumbAdapter);
        albumThumbAdapter.setOnClick(new BaseThumbAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                mainActivity.showDetailOneAlbum();
            }
        });
        homeBinding.albumRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }



    @Override
    public String getHeaderTitle() {
        return "Home";
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.albumRecyclerView:
//                mainActivity.showDetailOneAlbum();
//                break;
//
//            case R.id.artistRecyclerView:
//                mainActivity.showDetailOneArtist();
//                break;

            case R.id.show_detail_art:
                mainActivity.goToAllArtistList();
                break;

            case R.id.show_detail_alb:
                mainActivity.goToAllAlbumList();
                break;
        }

    }



    public void loadArtists() {
        homeBinding.loading.setVisibility(View.VISIBLE);
        APIService api = RetrofitClient.createClient();
        api.loadArtist("Bearer" + mainActivity.getUserToken(),0,10).enqueue(new Callback<APIResponse<ArtistListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<ArtistListResponse>> call, Response<APIResponse<ArtistListResponse>> response) {
                Log.e("TAG", "onResponse: Artist");

                APIResponse<ArtistListResponse> artistResponse = response.body();
                List<ArtistData> list = artistResponse.data.list_data;
                artistThumbAdapter.addData(list);
                homeBinding.loading.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<APIResponse<ArtistListResponse>> call, Throwable t) {
                Log.e("TAG", "onFailure: " );
                homeBinding.loading.setVisibility(View.GONE);

            }
        });
    }

    public void loadAlbums() {
        homeBinding.loading.setVisibility(View.VISIBLE);
        APIService api = RetrofitClient.createClient();
        api.loadAlbum("Bearer" + mainActivity.getUserToken(),0,10).enqueue(new Callback<APIResponse<AlbumListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumListResponse>> call, Response<APIResponse<AlbumListResponse>> response) {
                Log.e("TAG", "onResponse: Album");

                APIResponse<AlbumListResponse> albumResponse = response.body();
                List<AlbumData> list = albumResponse.data.list_data;
                albumThumbAdapter.addData(list);
                homeBinding.loading.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<APIResponse<AlbumListResponse>> call, Throwable t) {
                Log.e("TAG", "onFailure: " );
                homeBinding.loading.setVisibility(View.GONE);

            }
        });
    }
}
