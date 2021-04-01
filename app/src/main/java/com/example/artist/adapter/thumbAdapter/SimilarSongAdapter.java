package com.example.artist.adapter.thumbAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.adapter.viewholder.SimilarViewHolder;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.SongData;

import java.util.ArrayList;
import java.util.List;

public class SimilarSongAdapter extends RecyclerView.Adapter<SimilarViewHolder> {

    public List<AlbumData> listAll = new ArrayList<>();
    public final int VIEW_TYPE_LOADING = 1;
    public final int VIEW_TYPE_ITEM = 0;

    public SimilarSongAdapter(){}

    public void addData(List<AlbumData> listData) {
        listAll.addAll(listData);
        notifyDataSetChanged();
    }

    public void clear() {
        listAll.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SimilarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Dal5SimilarItemBinding similarBinding = Dal5SimilarItemBinding.inflate(inflater, parent, false);
        return new SimilarViewHolder(similarBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarViewHolder holder, int position) {
        AlbumData model = listAll.get(position);
        holder.bindView(model);
    }


    @Override
    public int getItemCount() {
        return listAll.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listAll.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM ;
    }

    protected void showLoadingView (LoadingViewHolder holder, int position){}
}