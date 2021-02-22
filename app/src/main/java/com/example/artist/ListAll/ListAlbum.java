package com.example.artist.ListAll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.ImageThumbnailBinding;

public class ListAlbum extends ListAllBase {

    private ImageThumbnailBinding binding;
    private MainActivity mainActivity;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.image_thumbnail, container, false);
        return binding.getRoot();
    }
}
