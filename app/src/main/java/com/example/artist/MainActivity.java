package com.example.artist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.artist.detailScreen.DetailOneAlbumFragment;
import com.example.artist.detailScreen.DetailOneArtistFragment;
import com.example.artist.homeScreen.HomeFragment;
import com.example.artist.listAll.ListAlbumFragment;
import com.example.artist.listAll.ListAllBaseFragment;
import com.example.artist.listAll.ListArtistsFragment;
import com.example.artist.login.LoginFragment;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.ActivityMainBinding;
import com.example.artist.login.ResponseLogin;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    FragmentManager fragMan = getSupportFragmentManager();
    public ResponseLogin responseLogin;
    public String mLoadedItemHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    public String getUserToken(){
        if (responseLogin != null) {
            return responseLogin.login_session.token;
        } else {
            return "";
        }
    }

    private void init(){
        responseLogin = ResponseLogin.getFromSharedPreference(this);
        if (responseLogin == null) {
            replaceFragment(new LoginFragment(), false);
        } else {
            Log.e("TAG", "init: " + responseLogin.toJsonString() );
            loginToHome();
        }

        binding.closeBtn.setOnClickListener(this);
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

    public void showDetailOneAlbum(AlbumData item) {
        DetailOneAlbumFragment fragment = DetailOneAlbumFragment.newInstance(item);
        replaceFragment(fragment, true);
    }

    public void showDetailOneArtist(ArtistData item) {
        DetailOneArtistFragment fragment = DetailOneArtistFragment.newInstance(item);
        replaceFragment(fragment, true);
    }

    public void goToAllArtistList() {
        replaceFragment(new ListArtistsFragment(), true);
    }

    public void goToAllAlbumList() {
        replaceFragment(new ListAlbumFragment(), true);
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
        boolean isListArtist = fr instanceof ListArtistsFragment;
        boolean isListAlbum = fr instanceof ListAlbumFragment;
        binding.textTittle.setText(title);
        binding.avatar.setVisibility(isHome ? View.VISIBLE : View.GONE);
        if (isHome || isLogin) {
            binding.closeBtn.setImageResource(R.drawable.ic_close);
        } else {
            binding.closeBtn.setImageResource(R.drawable.ic_arrow_back);
        }

        if (isListAlbum || isListArtist ){
            binding.loadedItem.setVisibility(View.VISIBLE);
            binding.loadedItem.setText(mLoadedItemHeader);
        } else {
            binding.loadedItem.setVisibility(View.INVISIBLE);
        }
    }
}