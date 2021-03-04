package com.example.artist.adapter.baseadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.ThumbnailViewHolder;
import com.example.artist.databinding.ImageThumbnailBinding;
import com.example.artist.model.BaseModelList;

import java.util.ArrayList;
import java.util.List;

public class BaseThumbAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {

    //protected ImageThumbnailBinding thumbnailBinding;
    public ClickListener clickListener;

    public List<BaseModelList> listAll = new ArrayList<>();

    public BaseThumbAdapter(){}

    public void addData(List<? extends BaseModelList> listData) {
        listAll.addAll(listData);
        notifyDataSetChanged();
    }

    //** Create method get Item by positions in listAll


    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImageThumbnailBinding thumbnailBinding = ImageThumbnailBinding.inflate(inflater, parent, false);
        return new ThumbnailViewHolder(thumbnailBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
        BaseModelList model = listAll.get(position);
        Log.d("xxx", getClass().getSimpleName() + " - onBindViewHolder position: " + position + ", model: " + model.name);
        bindData(holder, model);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position,v);
            }
        });
    }

    @Override
    public int getItemCount() { return listAll.size();
    }

    protected void  bindData(ThumbnailViewHolder holder, BaseModelList model) {
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void getItemByPosition(){

    }

    public void setOnClick(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }


}

