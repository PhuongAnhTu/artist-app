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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.adapter.thumbAdapter.DetailScreenAdapter;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.model.AlbumData;
import com.example.artist.util.LogUtil;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailAlbumFragment extends FragmentBase implements View.OnClickListener {

    protected DetailBaseLayoutBinding binding;
    private MainActivity mainActivity;
    public AlbumData selectedAlbumItem;
    protected DetailScreenAdapter adapter = new DetailScreenAdapter();

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
}
