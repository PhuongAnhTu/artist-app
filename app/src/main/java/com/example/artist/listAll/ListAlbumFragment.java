package com.example.artist.listAll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.baseadapter.ListArtistAdapter;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.DetailBaseLayoutBinding;

public class ListAlbumFragment extends ListAllBaseFragment {

    private DetailBaseLayoutBinding binding;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_base_layout, container, false);
        setupRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void setupRecyclerView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListArtistAdapter adapter = new ListArtistAdapter();
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
