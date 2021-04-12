package com.example.artist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupMenu;

import com.example.artist.detailScreen.DetailOneArtistFragment;
import com.example.artist.detailScreen.NewDetailAlbumFragment;
import com.example.artist.homeScreen.HomeFragment;
import com.example.artist.listAll.ListAlbumFragment;
import com.example.artist.listAll.ListArtistsFragment;
import com.example.artist.login.LoginFragment;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.ActivityMainBinding;
import com.example.artist.login.ResponseLogin;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;
import com.example.artist.playAudio.PlayAudioFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public ActivityMainBinding mainBinding;
    FragmentManager fragMan = getSupportFragmentManager();
    public ResponseLogin responseLogin;
    public String mLoadedItemHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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

        mainBinding.closeBtn.setOnClickListener(this);
        mainBinding.avatar.setOnClickListener(this);
    }

    private void replaceFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragTransaction = fragMan.beginTransaction().setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_left,
                R.anim.exit_to_left
        );
        fragMan.getBackStackEntryCount();
        if (isAddToBackStack) {
            fragTransaction.addToBackStack(null);
        }
        fragTransaction.replace(mainBinding.container.getId(), fragment, fragment.getClass().getSimpleName());
        fragTransaction.commit();
    }

    public void loginToHome (){
        replaceFragment(new HomeFragment(), false);
    }

    public void showDetailOneAlbum(AlbumData item) {
        NewDetailAlbumFragment fragment = NewDetailAlbumFragment.newInstance(item);
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

    @SuppressLint("NonConstantResourceId")
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

            case R.id.avatar:
                onAvatarClick(mainBinding.avatar);
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

        mainBinding.textTittle.setText(title);
        mainBinding.avatar.setVisibility(isHome ? View.VISIBLE : View.GONE);

        if (isHome || isLogin) {
            mainBinding.closeBtn.setImageResource(R.drawable.ic_close);
        } else {
            mainBinding.closeBtn.setImageResource(R.drawable.ic_arrow_back);
        }

        if (isListAlbum || isListArtist ){
            mainBinding.loadedItem.setVisibility(View.VISIBLE);
            mainBinding.loadedItem.setText(mLoadedItemHeader);
        } else {
            mainBinding.loadedItem.setVisibility(View.INVISIBLE);
        }
    }

    public void onAvatarClick (View avatar) {
        PopupMenu popupMenu = new PopupMenu(this, avatar);
        popupMenu.getMenuInflater().inflate(R.menu.logout_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logout){
                    logout();
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    public void logout () {
        SharedPreferences sharePref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharePref.edit();
        editor.clear().apply();
        responseLogin = ResponseLogin.getFromSharedPreference(this);
        replaceFragment(new LoginFragment(), false);
    }

    public void hideKeyboard () {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void sendPlayer (){

    }
}