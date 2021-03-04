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
import com.example.artist.adapter.thumbAdapter.ListArtistAdapter;
import com.example.artist.model.ArtistData;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.DetailBaseLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArtistsFragment extends ListAllBaseFragment {

    private MainActivity mainActivity;
    private ListArtistAdapter adapter= new ListArtistAdapter();
    private int total = 0;

    @Override
    public void onAttach (@NonNull Context context) {

        super.onAttach(context);
        if (context instanceof MainActivity){
            this.mainActivity = (MainActivity)context;
        }
    }

    @Override
    public String getHeaderTitle() {
        return "List artists";
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        mainActivity.setTitle(R.string.artist);
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void setupRecyclerView(){
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
    protected void loadMore() {
        loadData();
        updateLoadedItemString();
        mainActivity.updateHeader();
    }

    @Override
    protected void refresh() {
        adapter.clear();
        super.refresh();
        loadData();
    }

    @Override
    public void updateLoadedItemString() {
        mLoadedItem = adapter.getItemCount();
        mTotal = total;
        super.updateLoadedItemString();
    }


    public void loadData() {
        startLoading();
        APIService api = RetrofitClient.createClient();
        api.loadArtist("Bearer" + mainActivity.getUserToken(),adapter.getItemCount(), 10).enqueue(new Callback<APIResponse<ArtistListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<ArtistListResponse>> call, Response<APIResponse<ArtistListResponse>> response) {
                Log.e("TAG", "onResponse: ");
                APIResponse<ArtistListResponse> artistResponse = response.body();
                adapter.addData(artistResponse.data.list_data);
                if (adapter.getItemCount() == artistResponse.data.total) {
                    isFullData = true;
                }
                stopLoading();
                total = artistResponse.data.total;
                updateLoadedItemString();
                mainActivity.updateHeader();
            }
            @Override
            public void onFailure(Call<APIResponse<ArtistListResponse>> call, Throwable t) {
                stopLoading();
                Log.e("TAG", "onFailure: " );

            }
        });
    }
}

