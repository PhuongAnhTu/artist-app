package com.example.artist.ListAll;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.Adapter;
import com.example.artist.MainActivity;
import com.example.artist.R;
import com.example.artist.databinding.DetailOneBinding;
import com.example.artist.databinding.ImageThumbnailBinding;

import java.util.List;

public class ListArtists extends ListAllBase {

    private ImageThumbnailBinding binding;
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
    public String getHeaderTitle() {
        return "List artists";
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        mainActivity.setTitle(R.string.artist);
        binding = DataBindingUtil.inflate(inflater, R.layout.image_thumbnail, container, false);
        return binding.getRoot();
    }


//    @Override
//    protected void setupRecyclerView() {
//
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        Adapter adapter = new Adapter(getContext());
//        binding.recyclerView.setAdapter(adapter);
//        listImages = mainActivity.fetchImage(5, 0);
//        adapter.addData(listImages);

//        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//
////                super.onScrollStateChanged(recyclerView, newState);
//                if (!recyclerView.canScrollVertically(1)){
//                    countScroll = countScroll + 1;
//                    List<String> l = mainActivity.fetchImage(5, countScroll*limit);
//                    if (l.size() < limit) {
//                        listImages.addAll(l);
//                        adapter.addData(listImages);
//                        binding.recyclerView.setNestedScrollingEnabled(false);
//                    } else {
//                        listImages.addAll(l);
//                        adapter.addData(listImages);
//                    }
//                }
//            }
//        });


    }
//
//    @Override
//    protected void refresh() {
//        mainActivity.fetchImage(listImages.size(), 0);
//        Toast.makeText(mainActivity.getApplicationContext(), "List View Works!", Toast.LENGTH_LONG).show();
//        Log.e("XX", "refresh: "+ "List View Works!");
//        super.binding.swipeRefresh.setRefreshing(false);
//    }
//}

