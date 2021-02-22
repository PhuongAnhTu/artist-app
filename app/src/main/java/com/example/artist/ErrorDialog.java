package com.example.artist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.artist.databinding.PhotoDialogBinding;

public class ErrorDialog extends Dialog {

    private PhotoDialogBinding binding;
    public ErrorDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.photo_dialog, null, false);
        setContentView(binding.getRoot());
    }
}
