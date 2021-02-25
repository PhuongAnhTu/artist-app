package com.example.artist.baseadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.model.BaseModelList;

import java.util.ArrayList;
import java.util.List;

public class BaseThumbAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
    ImageThumbnailBinding binding;

    public List<BaseModelList> listAll = new ArrayList<>();

    public BaseThumbAdapter(){}

    public void addData(List<? extends BaseModelList> listData) {
        listAll.addAll(listData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ImageThumbnailBinding.inflate(inflater, parent, false);
        return new ThumbnailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
        BaseModelList model = listAll.get(position);
        bindData(holder, model);
    }

    @Override
    public int getItemCount() { return listAll.size();
    }

    protected void  bindData(ThumbnailViewHolder holder, BaseModelList model) {
    }
}

