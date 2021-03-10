package com.example.artist.detailScreen;

import android.content.Context;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.SharePref;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailOneBinding;
import com.example.artist.listAll.ArtistListResponse;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOneAlbumFragment extends FragmentBase {
    private DetailOneBinding binding;
    private MainActivity mainActivity;
    private AlbumData selectedAlbumItem;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_one, container, false);
        loadDetail();
        return binding.getRoot();
    }

    @Override
    public String getHeaderTitle() {
        return "Detail Album";
    }

    public void loadDetail() {
        APIService api = RetrofitClient.createClient();
        api.loadDetailAlbum("Bearer" + mainActivity.getUserToken(), selectedAlbumItem._id).enqueue(new Callback<APIResponse<AlbumDetailResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AlbumDetailResponse>> call, Response<APIResponse<AlbumDetailResponse>> response) {
                APIResponse<AlbumDetailResponse> detail = response.body();
                Context context = binding.getRoot().getContext();
                if (detail.data.images != null && detail.data.images.size() > 0) {
                    String imageUrl = "https://file.thedarkmetal.com/" + detail.data.images.get(0);
                    Glide.with(context)
                         .load(imageUrl)
                         .into(binding.image);
                }

                binding.name.setText(detail.data.name);
                binding.text2.setText(R.string.length);
                binding.text3.setText(R.string.released);
                binding.edtText2.setText(detail.data.length);
                binding.country.setText(detail.data.released);
            }

            @Override
            public void onFailure(Call<APIResponse<AlbumDetailResponse>> call, Throwable t) {
                Log.e("TAG", "onFailure: ");
            }
        });
    }
}

