package com.example.artist.adapter.baseadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.AlbumImageCardViewVH;
import com.example.artist.adapter.viewholder.DetailViewHolder;
import com.example.artist.adapter.viewholder.LabelSimilarVH;
import com.example.artist.adapter.viewholder.LabelSongVH;
import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.adapter.viewholder.SimilarViewHolder;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.databinding.Dal1ImageCardviewBinding;
import com.example.artist.databinding.Dal2LabelListSongBinding;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.databinding.Dal4LabelSimilarBinding;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.databinding.DetailBaseLayoutBinding;
import com.example.artist.databinding.ImageItemBinding;
import com.example.artist.databinding.LoadingItemBinding;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;

import java.util.ArrayList;
import java.util.List;

public class BaseItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<BaseModelList> listAll = new ArrayList<>();
    public final int VIEW_TYPE_LOADING = 1;
    public final int VIEW_TYPE_DETAIL = 0;


    public BaseItemAdapter(){}

    public void addData(List<? extends BaseModelList> listData) {
        listAll.addAll(listData);
        notifyDataSetChanged();
    }

    public void clear() {
        listAll.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_DETAIL) {
            ImageItemBinding binding = ImageItemBinding.inflate(inflater, parent, false);
            return new DetailViewHolder (binding);
        } else {
            LoadingItemBinding itemBinding = LoadingItemBinding.inflate(inflater, parent, false);
            return new LoadingViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseModelList model = listAll.get(position);

        if (holder instanceof DetailViewHolder){
            bindData((DetailViewHolder) holder, model);
        } else {
            showLoadingView ((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return listAll.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;

        if(listAll.get(position) == null ){
            viewType = VIEW_TYPE_LOADING;
        } else {
            viewType = VIEW_TYPE_DETAIL;
        }

        return viewType;
    }



    protected void  bindData( DetailViewHolder holder , BaseModelList model) {}
    protected void showLoadingView (LoadingViewHolder holder, int position){}
}
