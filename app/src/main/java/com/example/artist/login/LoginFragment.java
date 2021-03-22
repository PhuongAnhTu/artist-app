package com.example.artist.login;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.artist.API.APIResponse;
import com.example.artist.API.APIService;
import com.example.artist.API.RetrofitClient;
import com.example.artist.MainActivity;
import com.example.artist.ErrorDialog;
import com.example.artist.R;
import com.example.artist.SharePref;
import com.example.artist.model.User;
import com.example.artist.base.FragmentBase;
import com.example.artist.databinding.LoginFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends FragmentBase implements View.OnClickListener {
    private LoginFragmentBinding binding;
    private final User user = new User();
    private MainActivity mainActivity;

    public LoginFragment(){}


    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        init();
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyBoard();
                return false;
            }
        });
        return binding.getRoot();
    }

    private void init(){
        binding.loginButton.setOnClickListener(this);
        binding.edtEmailLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    user.email = s.toString();
                }
            }
        });
        binding.edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    user.pass = s.toString();
                }
            }
        });
    }

    @Override
    public String getHeaderTitle() {
        return "Login";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            if (validationSuccess()) {
                login();
            }
        }
    }

    void login(){
        APIService api = RetrofitClient.createClient();
        showLoading();
        api.login(new LoginModel(user.email, user.pass)).enqueue(new Callback<APIResponse<ResponseLogin>>() {
            @Override
            public void onResponse(Call<APIResponse<ResponseLogin>> call, Response<APIResponse<ResponseLogin>> response) {
                hideLoading();
                Log.e("TAG", "onResponse: " + "success" );
                mainActivity.loginToHome();
                APIResponse<ResponseLogin> loginAPIResponse = response.body();
                mainActivity.responseLogin = loginAPIResponse.data;
                SharePref.setLoginData(getContext(), mainActivity.responseLogin);
                Log.d("TAG", "loginAPIResponse: " + mainActivity.responseLogin.toJsonString());
            }

            @Override
            public void onFailure(Call<APIResponse<ResponseLogin>> call, Throwable t) {
                hideLoading();
                Log.e("xxx", "onFailure" + t);
            }
        });
    }

    private void showLoading() {
        binding.loginButton.setVisibility(View.GONE);
        binding.progressLoader.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.loginButton.setVisibility(View.VISIBLE);
        binding.progressLoader.setVisibility(View.GONE);
    }

    private boolean validationSuccess () {
        if (binding.edtEmailLogin.getText().toString().length() <= 1 ){
            Toast.makeText(mainActivity.getApplicationContext(), "Please input email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (binding.edtPass.getText().toString().length() <= 1 || binding.edtPass.getText().toString().length() >= 20) {
            Toast.makeText(mainActivity.getApplicationContext(),
                                        "Password must be between 1 and 20 characters in length ",
                                        Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!binding.edtEmailLogin.getText().toString().equals("user7mq@gmail.com")) {
            ErrorDialog dialog = new ErrorDialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return false;
        }

        return true;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach (@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            this.mainActivity = (MainActivity)context;
        }
    }
}
