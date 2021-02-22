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
import com.example.artist.AdapterBase;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.HomeFragmentBinding;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.login.LoginModel;
import com.example.artist.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends FragmentBase implements View.OnClickListener {
    private HomeFragmentBinding homeBinding;
    private MainActivity mainActivity;
    private ImageThumbnailBinding thumbnailBinding;


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

    }

    //Call API

    public void createArtistRecyclerView(){
        homeBinding.artistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        AdapterBase adapter = new AdapterBase(getContext());
        homeBinding.artistRecyclerView.setAdapter(adapter);

        homeBinding.artistRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void createAlbumRecyclerView(){
        homeBinding.albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        AdapterBase adapter = new AdapterBase(getContext());
        homeBinding.albumRecyclerView.setAdapter(adapter);

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
            case R.id.albumRecyclerView:
                mainActivity.showDetailOneAlbum();
                break;

            case R.id.artistRecyclerView:
                mainActivity.showDetailOneArtist();
                break;

            case R.id.show_detail_art:
                mainActivity.goToAllArtistList();
                break;

            case R.id.show_detail_alb:
                mainActivity.goToAllAlbumList();
                break;
        }

    }
}
