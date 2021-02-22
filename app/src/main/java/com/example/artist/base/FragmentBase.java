package com.example.artist.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.artist.MainActivity;

public class FragmentBase extends Fragment {
    protected MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    public String getHeaderTitle() {
        return "";
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.updateHeader();
    }
}
