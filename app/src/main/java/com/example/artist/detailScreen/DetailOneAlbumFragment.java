package com.example.artist.detailScreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailOneBinding;

public class DetailOneAlbumFragment extends FragmentBase {
    private DetailOneBinding binding;
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
//        mainActivity.setTitle(R.string.detailAlbum);
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_one, container, false);
        return binding.getRoot();
    }

    @Override
    public String getHeaderTitle() {
        return "Detail Album";
    }
}
