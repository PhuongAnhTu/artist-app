package com.example.artist.adapter.thumbAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.model.SongData;

import java.util.ArrayList;
import java.util.List;

public class DetailAlbumSongAdapter extends RecyclerView.Adapter<SongViewHolder> {

        public List<SongData> listAll = new ArrayList<>();
        public final int VIEW_TYPE_LOADING = 1;
        public final int VIEW_TYPE_ITEM = 0;

        public DetailAlbumSongAdapter(){}

        public void addData(List<SongData> listData) {
            listAll.addAll(listData);
            notifyDataSetChanged();
        }

        public void clear() {
            listAll.clear();
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            Dal3ItemSongBinding songBinding = Dal3ItemSongBinding.inflate(inflater, parent, false);
            return new SongViewHolder(songBinding);
        }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
            SongData model = listAll.get(position);
            holder.bindView(holder,model);
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
