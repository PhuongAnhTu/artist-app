package com.example.artist.listAll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.MainActivity;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailBaseLayoutBinding;

public class ListAllBaseFragment extends FragmentBase {
    protected DetailBaseLayoutBinding binding;
    protected MainActivity mainActivity;
    protected boolean isLoading = false;
    protected boolean isFullData = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailBaseLayoutBinding.inflate(inflater, container, false);
        setupRecyclerView();

        return binding.getRoot();
    }

    protected void setupRecyclerView() {
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if (canLoadMore()) {
                        loadMore();
                    }
                }
            }
        });
    }

    private boolean canLoadMore() {
        return !isLoading && !isFullData;
    }

    protected void loadMore() {
    }

    public void startLoading() {
        isLoading = true;
        binding.loading.setVisibility(View.VISIBLE);
    }

    protected void stopLoading() {
        isLoading = false;
        binding.loading.setVisibility(View.GONE);
    }
}


