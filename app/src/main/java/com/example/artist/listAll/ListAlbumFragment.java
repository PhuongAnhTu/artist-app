package com.example.artist.listAll;

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
import com.example.artist.adapter.thumbAdapter.ListAlbumAdapter;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.model.AlbumData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlbumFragment extends ListAllBaseFragment {

    private MainActivity mainActivity;
    protected ListAlbumAdapter adapter = new ListAlbumAdapter();
    private int total = 0;
    List<AlbumData> list;


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

    @Override
    public String getHeaderTitle() {
        return "List albums";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity.setTitle(R.string.album);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        updateLoadedItemString();
        mainActivity.updateHeader();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadData();
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    public void updateLoadedItemString() {
        mLoadedItem = adapter.getItemCount();
        mTotal = total;
        super.updateLoadedItemString();
    }

    @Override
    protected void refresh() {
        List<AlbumData> newAlbum;
        newAlbum = list;
        list.clear();
        adapter.addData(newAlbum);
        super.refresh();
        updateLoadedItemString();
        mainActivity.updateHeader();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void loadMore() {
        loadData();
        updateLoadedItemString();
        mainActivity.updateHeader();
    }

    public void loadData() {
        startLoading();
        APIService api = RetrofitClient.createClient();
        api.loadAlbum("Bearer" + mainActivity.getUserToken(), adapter.getItemCount() , 10).enqueue(new Callback<APIResponse<AlbumListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumListResponse>> call, Response<APIResponse<AlbumListResponse>> response) {
                Log.e("TAG", "onResponse: ");
                APIResponse<AlbumListResponse> albumResponse = response.body();
                list = albumResponse.data.list_data;
                adapter.addData(list);
                if (adapter.getItemCount() == albumResponse.data.total) {
                    isFullData = true;
                }
                stopLoading();
                total = albumResponse.data.total;
                updateLoadedItemString();
                mainActivity.updateHeader();
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumListResponse>> call, Throwable t) {
                stopLoading();
                Log.e("TAG", "onFailure: " + t);

            }
        });
    }
}
