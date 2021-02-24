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
import com.example.artist.ListArtistAdapter;
import com.example.artist.ArtistData;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.DetailBaseLayoutBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArtists extends ListAllBase {

    private DetailBaseLayoutBinding binding;
    private MainActivity mainActivity;
    private ListArtistAdapter adapter= new ListArtistAdapter();;


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
    public String getHeaderTitle() {
        return "List artists";
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        mainActivity.setTitle(R.string.artist);
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_base_layout, container, false);
        setupRecyclerView();
        return binding.getRoot();
    }



    @Override
    public void setupRecyclerView(){
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

    public void loadData() {
        binding.loading.setVisibility(View.VISIBLE);
        APIService api = RetrofitClient.createClient();
        api.loadArtist().enqueue(new Callback<APIResponse<ArtistListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<ArtistListResponse>> call, Response<APIResponse<ArtistListResponse>> response) {
                Log.e("TAG", "onResponse: ");
                APIResponse<ArtistListResponse> artistResponse = response.body();
                List<ArtistData> list = artistResponse.data.list_data;
                adapter.addData(list);
                binding.loading.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<APIResponse<ArtistListResponse>> call, Throwable t) {
                binding.loading.setVisibility(View.GONE);
                Log.e("TAG", "onFailure: " );

            }
        });
    }


    //
//    @Override
//    protected void refresh() {
//        mainActivity.fetchImage(listImages.size(), 0);
//        Toast.makeText(mainActivity.getApplicationContext(), "List View Works!", Toast.LENGTH_LONG).show();
//        Log.e("XX", "refresh: "+ "List View Works!");
//        super.binding.swipeRefresh.setRefreshing(false);
//    }
}

