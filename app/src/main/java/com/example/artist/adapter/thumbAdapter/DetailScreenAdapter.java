package com.example.artist.adapter.thumbAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.AlbumImageCardViewVH;
import com.example.artist.adapter.viewholder.DetailViewHolder;
import com.example.artist.adapter.viewholder.LabelSimilarVH;
import com.example.artist.adapter.viewholder.LabelSongVH;
import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.adapter.viewholder.SimilarViewHolder;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.databinding.Dal1ImageCardviewBinding;
import com.example.artist.databinding.Dal2LabelListSongBinding;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.databinding.Dal4LabelSimilarBinding;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.databinding.LoadingItemBinding;
import com.example.artist.model.AlbumData;
import com.example.artist.model.BaseModelList;
import com.example.artist.model.SongData;

import java.util.ArrayList;
import java.util.List;

public class DetailScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AlbumData albumData;
    private List<SongData> listSong = new ArrayList<>();
    public List<AlbumData> listSimilarAlbum = new ArrayList<>();

    public void setAlbumData(AlbumData albumData) {
        this.albumData = albumData;
        notifyDataSetChanged();
    }

    public void setSimilarAlbum(List<AlbumData> list) {
        this.listSimilarAlbum = list;
        notifyDataSetChanged();
    }

    public void setListSongs(List<SongData> list) {
        this.listSong = list;
        notifyDataSetChanged();;
    }








    public AlbumImageCardViewVH imageCardViewVH;
    public LabelSongVH labelSongVH;
    public LabelSimilarVH labelSimilarVH;
    public SimilarViewHolder similarViewHolder;
    public SongViewHolder songViewHolder;
    public final int VIEW_TYPE_LOADING = 1;
    public final int VIEW_TYPE_DETAIL_ALBUM = 2;
    public final int VIEW_TYPE_DETAIL_LIST_SONG_LABEL = 3;
    public final int VIEW_TYPE_DETAIL_SONG_ITEM = 4;
    public final int VIEW_TYPE_DETAIL_SIMILAR_LABEL = 5;
    public final int VIEW_TYPE_DETAIL_SIMILAR_ITEM = 6;


    public DetailScreenAdapter(){}


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_DETAIL_ALBUM) {
            Dal1ImageCardviewBinding binding = Dal1ImageCardviewBinding.inflate(inflater,parent,false);
            imageCardViewVH = new AlbumImageCardViewVH(binding);
            return imageCardViewVH;
        }
        else if (viewType == VIEW_TYPE_DETAIL_SIMILAR_ITEM) {
            Dal5SimilarItemBinding binding = Dal5SimilarItemBinding.inflate(inflater, parent, false);

            similarViewHolder = new SimilarViewHolder(binding);
            return similarViewHolder;
        }
        else if (viewType == VIEW_TYPE_DETAIL_LIST_SONG_LABEL){
            Dal2LabelListSongBinding binding = Dal2LabelListSongBinding.inflate(inflater,parent, false);
            labelSongVH = new LabelSongVH(binding);
            return labelSongVH;
        }
        else if (viewType == VIEW_TYPE_DETAIL_SONG_ITEM){
            Dal3ItemSongBinding binding = Dal3ItemSongBinding.inflate(inflater, parent, false);
            songViewHolder = new SongViewHolder(binding);
            return songViewHolder;
        }
        else if (viewType == VIEW_TYPE_DETAIL_SIMILAR_LABEL){
            Dal4LabelSimilarBinding binding = Dal4LabelSimilarBinding.inflate(inflater,parent,false);
            labelSimilarVH = new LabelSimilarVH(binding);
            return labelSimilarVH;
        }
        else {
            LoadingItemBinding itemBinding = LoadingItemBinding.inflate(inflater, parent, false);
            return new LoadingViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 0: album detail
        // 1: label
        // 2: song[index]
        // ...
        // 2+songs.size()
        // label
        // album[index]
        if (holder instanceof AlbumImageCardViewVH){
            imageCardViewVH.bindView((AlbumImageCardViewVH) holder, albumData);
        } else if (holder instanceof LabelSongVH){
            labelSongVH.bindView((LabelSongVH) holder);
        } else if (holder instanceof SongViewHolder){
            int songPosition = position - 2;
            SongData song = listSong.get(songPosition);
            songViewHolder.bindView ((SongViewHolder) holder, song);
        } else if (holder instanceof LabelSimilarVH){
            labelSimilarVH.bindView((LabelSimilarVH) holder);
        } else if (holder instanceof SimilarViewHolder){
            int similarPosition = position - listSong.size() - 1 - 2;
            AlbumData album = listSimilarAlbum.get(similarPosition);
            similarViewHolder.bindView((SimilarViewHolder) holder, album);
        }
    }

    @Override
    public int getItemCount() {
        return 1 + 1 + listSong.size() + 1 + listSimilarAlbum.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        int lastSongPosition = 2 + listSong.size() - 1;
        if (position == 0){
            viewType = VIEW_TYPE_DETAIL_ALBUM;
        } else if (position == 1){
            viewType = VIEW_TYPE_DETAIL_LIST_SONG_LABEL;
        } else if (position >= 2 && position <= lastSongPosition){
            viewType = VIEW_TYPE_DETAIL_SONG_ITEM;
        } else if (position == lastSongPosition + 1){
            viewType = VIEW_TYPE_DETAIL_SIMILAR_LABEL;
        } else {
            viewType = VIEW_TYPE_DETAIL_SIMILAR_ITEM;
        }
        return viewType;
    }


    protected void  bindData(DetailViewHolder holder , BaseModelList model) {}
    protected void showLoadingView (LoadingViewHolder holder, int position){}
}