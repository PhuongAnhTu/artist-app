package com.example.artist.base;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.artist.MainActivity;

public class FragmentBase extends Fragment {
    protected MainActivity mainActivity;
    protected boolean isLoading = false;
    protected int mLoadedItem = 0;

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

    protected void hideSoftKeyBoard() {
        mainActivity.hideKeyboard();
    }
}
