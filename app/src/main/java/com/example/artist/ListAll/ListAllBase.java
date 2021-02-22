package com.example.artist.ListAll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.artist.MainActivity;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.DetailBaseLayoutBinding;

public class ListAllBase extends FragmentBase {
    protected DetailBaseLayoutBinding binding;
    protected MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailBaseLayoutBinding.inflate(inflater, container, false);

//        binding.container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refresh();
//                Toast.makeText(mainActivity.getApplicationContext(), "Works!", Toast.LENGTH_LONG).show();
//            }
//        });
        setupRecyclerView();

        return binding.getRoot();
    }

    protected void setupRecyclerView() {
    }

//    protected void refresh() {
//    }

}


