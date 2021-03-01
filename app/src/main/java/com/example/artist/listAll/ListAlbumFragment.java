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
import com.example.artist.SharePref;
import com.example.artist.adapter.ListAlbumAdapter;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.model.AlbumData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlbumFragment extends ListAllBaseFragment {

    private DetailBaseLayoutBinding binding;
    private MainActivity mainActivity;
    ListAlbumAdapter adapter = new ListAlbumAdapter();


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
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_base_layout, container, false);
        setupRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void setupRecyclerView() {
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
        api.loadAlbum("Bearer" + mainActivity.getUserToken(), 10, 10).enqueue(new Callback<APIResponse<AlbumListResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumListResponse>> call, Response<APIResponse<AlbumListResponse>> response) {
                Log.e("TAG", "onResponse: ");
                APIResponse<AlbumListResponse> albumResponse = response.body();
                List<AlbumData> list = albumResponse.data.list_data;
                adapter.addData(list);
                binding.loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumListResponse>> call, Throwable t) {
                binding.loading.setVisibility(View.GONE);
                Log.e("TAG", "onFailure: ");

            }
        });
    }
}
