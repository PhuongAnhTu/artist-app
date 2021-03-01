package com.example.artist.adapter.baseadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.DetailViewHolder;
import com.example.artist.databinding.ImageItemBinding;
import com.example.artist.model.BaseModelList;

import java.util.ArrayList;
import java.util.List;

public class BaseItemAdapter extends RecyclerView.Adapter<DetailViewHolder> {
    public ImageItemBinding itemBinding;

    public List<BaseModelList> listAll = new ArrayList<>();

    public BaseItemAdapter(){}

    public void addData(List<? extends BaseModelList> listData) {
        listAll.addAll(listData);
        notifyDataSetChanged();
        }


    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemBinding = ImageItemBinding.inflate(inflater, parent, false);
            return new DetailViewHolder(itemBinding);
            }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
            BaseModelList model = listAll.get(position);
            bindData(holder, model);
            }

    @Override
    public int getItemCount() { return listAll.size();
            }

    protected void  bindData(DetailViewHolder holder, BaseModelList model) {
            }
        }
