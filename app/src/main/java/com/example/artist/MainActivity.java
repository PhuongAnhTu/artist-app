package com.example.artist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.artist.DetailScreen.DetailOneAlbum;
import com.example.artist.DetailScreen.DetailOneArtist;
import com.example.artist.ListAll.ListAlbum;
import com.example.artist.ListAll.ListArtists;
import com.example.artist.Login.LoginFragment;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    FragmentManager fragMan = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init(){
        binding.closeBtn.setOnClickListener(this);
        replaceFragment(new LoginFragment(), false);
    }

    private void replaceFragment(Fragment fragment, boolean isAddBackToTack) {
        FragmentTransaction fragTransaction = fragMan.beginTransaction().setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_left,
                R.anim.exit_to_left
        );
        fragMan.getBackStackEntryCount();
        if (isAddBackToTack) {
            fragTransaction.addToBackStack(null);
        }
        fragTransaction.replace(binding.container.getId(), fragment, fragment.getClass().getSimpleName());
        fragTransaction.commit();
    }

    public void loginToHome (){
        replaceFragment(new HomeFragment(), false);
    }

    public void showDetailOneAlbum() {
        replaceFragment(new DetailOneAlbum(), true);
    }

    public void showDetailOneArtist() {
        replaceFragment(new DetailOneArtist(), true);
    }

    public void goToAllArtistList() {
        replaceFragment(new ListArtists(), true);
    }

    public void goToAllAlbumList() {
        replaceFragment(new ListAlbum(), true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_btn:
                int count = fragMan.getBackStackEntryCount();
                if (count < 1) {
                    finish();
                } else {
                    onBackPressed();
                    updateHeader();
                }
                break;
        }
    }

    private Fragment getCurrentFragment() {
        return fragMan.findFragmentById(R.id.container);
    }

    public void updateHeader() {
        String title = "";
        Fragment fr = getCurrentFragment();
        if (fr instanceof FragmentBase) {
            title = ((FragmentBase)fr).getHeaderTitle();
        }
        boolean isHome = fr instanceof HomeFragment;
        boolean isLogin = fr instanceof LoginFragment;
        binding.textTittle.setText(title);
        binding.avatar.setVisibility(isHome ? View.VISIBLE : View.GONE);
        if (isHome || isLogin) {
            binding.closeBtn.setImageResource(R.drawable.ic_close);
        } else {
            binding.closeBtn.setImageResource(R.drawable.ic_arrow_back);
        }
    }
}