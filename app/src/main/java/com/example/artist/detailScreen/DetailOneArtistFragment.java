//package com.example.artist.detailScreen;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.databinding.DataBindingUtil;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.artist.API.APIResponse;
//import com.example.artist.API.APIService;
//import com.example.artist.API.RetrofitClient;
//import com.example.artist.MainActivity;
//import com.example.artist.R;
//import com.example.artist.adapter.viewholder.DetailViewHolder;
//import com.example.artist.base.FragmentBase;
//import com.example.artist.databinding.DetailBaseLayoutBinding;
//import com.example.artist.databinding.DetailOneBinding;
//import com.example.artist.listAll.ListAllBaseFragment;
//import com.example.artist.model.ArtistData;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class DetailOneArtistFragment extends FragmentBase {
//    private DetailOneBinding binding;
//    private MainActivity mainActivity;
//    private ArtistData selectedItem;
//
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//
//        super.onAttach(context);
//        if (context instanceof MainActivity) {
//            this.mainActivity = (MainActivity) context;
//        }
//    }
//
//    public static DetailOneArtistFragment newInstance(ArtistData artistData) {
//        DetailOneArtistFragment fragment = new DetailOneArtistFragment();
//        Bundle b = new Bundle();
//        b.putSerializable("artist", artistData);
//        fragment.setArguments(b);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        Bundle bundle = getArguments();
//        if (bundle != null){
//            selectedItem = (ArtistData) bundle.getSerializable("artist");
//        }
//        binding = DataBindingUtil.inflate(inflater, R.layout.detail_one, container, false);
//        loadDetail();
//        return binding.getRoot();
//    }
//
//    @Override
//    public String getHeaderTitle() {
//        return "Detail Artist";
//    }
//
//    public void loadDetail() {
//        APIService api = RetrofitClient.createClient();
//        api.loadDetailArtist("Bearer" + mainActivity.getUserToken(), selectedItem._id).enqueue(new Callback<APIResponse<ArtistDetailResponse>>() {
//            @Override
//            public void onResponse(Call<APIResponse<ArtistDetailResponse>> call, Response<APIResponse<ArtistDetailResponse>> response) {
//                APIResponse<ArtistDetailResponse> detail = response.body();
//                Context context = binding.getRoot().getContext();
//                if (detail.data.images != null && detail.data.images.size() > 0) {
//                    String imageUrl = "https://file.thedarkmetal.com/" + detail.data.images.get(0);
//                    Glide.with(context)
//                         .load(imageUrl)
//                         .into(binding.image);
//                }
//
//                binding.name.setText(detail.data.name);
//                binding.edtText2.setText(detail.data.genres);
//                binding.country.setText(detail.data.country.name);
//            }
//
//            @Override
//            public void onFailure(Call<APIResponse<ArtistDetailResponse>> call, Throwable t) {
//                Log.e("TAG", "onFailure: " + t);
//            }
//        });
//    }
//}
//
