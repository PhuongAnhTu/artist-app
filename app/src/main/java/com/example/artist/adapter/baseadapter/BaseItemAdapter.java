package com.example.artist.adapter.baseadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.DetailViewHolder;
import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.databinding.ImageItemBinding;
import com.example.artist.databinding.LoadingItemBinding;
import com.example.artist.model.BaseModelList;

import java.util.ArrayList;
import java.util.List;

public class BaseItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<BaseModelList> listAll = new ArrayList<>();
    public final int VIEW_TYPE_LOADING = 1;
    public final int VIEW_TYPE_ITEM = 0;

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

        if (viewType == VIEW_TYPE_ITEM) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ImageItemBinding itemBinding = ImageItemBinding.inflate(inflater, parent, false);
            return new DetailViewHolder(itemBinding);
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            LoadingItemBinding itemBinding = LoadingItemBinding.inflate(inflater, parent, false);
            return new LoadingViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            BaseModelList model = listAll.get(position);

            if (holder instanceof DetailViewHolder){

                bindData((DetailViewHolder) holder, model);
            } else if (holder instanceof LoadingViewHolder){
                showLoadingView ((LoadingViewHolder) holder, position);
            }
            }

    @Override
    public int getItemCount() { return listAll.size();
            }

    protected void  bindData( DetailViewHolder holder , BaseModelList model) {
            }
    protected void showLoadingView (LoadingViewHolder holder, int position){

            }

    @Override
    public int getItemViewType(int position) {
        return listAll.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM ;
    }
}
