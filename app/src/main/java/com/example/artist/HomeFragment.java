package com.example.artist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.HomeFragmentBinding;

public class HomeFragment extends FragmentBase implements View.OnClickListener {
    private HomeFragmentBinding binding;
    private MainActivity mainActivity;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);

        mainActivity.getSupportActionBar().hide();
        init();
        return binding.getRoot();
    }

    private void init () {
        binding.showDetailArt.setOnClickListener(this);
        binding.albumImage.setOnClickListener(this);
        binding.artistImage.setOnClickListener(this);
        binding.showDetailAlb.setOnClickListener(this);
    }

    @Override
    public String getHeaderTitle() {
        return "Home";
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.album_image:
                mainActivity.showDetailOneAlbum();
                break;

            case R.id.artist_image:
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
