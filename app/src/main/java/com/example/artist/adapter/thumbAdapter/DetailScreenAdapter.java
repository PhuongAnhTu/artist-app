package com.example.artist.adapter.thumbAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.AlbumImageCardViewVH;
import com.example.artist.adapter.viewholder.LabelVH;
import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.adapter.viewholder.SimilarViewHolder;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.databinding.Dal1ImageAlbumCardviewBinding;
import com.example.artist.databinding.Dal2LabelTextBinding;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.databinding.LoadingItemBinding;
import com.example.artist.detailScreen.NewDetailAlbumFragment;
import com.example.artist.model.AlbumData;
import com.example.artist.model.SongData;
import com.google.android.exoplayer2.ExoPlayer;

import java.util.ArrayList;
import java.util.List;

public class DetailScreenAdapter<PlaybackStateListener> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AlbumData albumData;
    private List<SongData> listSong = new ArrayList<>();
    public List<AlbumData> listSimilarAlbum = new ArrayList<>();
    public AlbumImageCardViewVH imageCardViewVH;
    public LabelVH labelVH;
    public final int VIEW_TYPE_DETAIL_ALBUM = 7;
    public final int VIEW_TYPE_LABEL = 3;
    public final int VIEW_TYPE_DETAIL_SONG_ITEM = 4;
    public final int VIEW_TYPE_DETAIL_SIMILAR_ITEM = 6;


    public final int SONG_LABEL = 0;
    public final int SIMILAR_LABEL = 1;
    public final int ALBUM_LABEL = 2;
    protected Dal3ItemSongBinding songBinding;
    private SongViewHolder.Listener songListener;
    private SimilarViewHolder.Listener similarListener;
    private ExoPlayer player;
    private static final String TAG = NewDetailAlbumFragment.class.getName();

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

    public void clear(){
        listSong.clear();
        listSimilarAlbum.clear();
        notifyDataSetChanged();
    }


    public DetailScreenAdapter(SongViewHolder.Listener songListener, ExoPlayer player, SimilarViewHolder.Listener similarListener){
        this.songListener = songListener;
        this.player = player;
        this.similarListener = similarListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_DETAIL_ALBUM) {
            Dal1ImageAlbumCardviewBinding binding = Dal1ImageAlbumCardviewBinding.inflate(inflater,parent,false);
            imageCardViewVH = new AlbumImageCardViewVH(binding);
            return imageCardViewVH;
        }
        else if (viewType == VIEW_TYPE_DETAIL_SIMILAR_ITEM) {
            Dal5SimilarItemBinding binding = Dal5SimilarItemBinding.inflate(inflater, parent, false);
            return new SimilarViewHolder(binding, similarListener );
        }
        else if (viewType == VIEW_TYPE_LABEL){
            Dal2LabelTextBinding binding = Dal2LabelTextBinding.inflate(inflater,parent, false);
            labelVH = new LabelVH(binding);
            return labelVH;
        }
        else if (viewType == VIEW_TYPE_DETAIL_SONG_ITEM){
            songBinding = Dal3ItemSongBinding.inflate(inflater, parent, false);
            return new SongViewHolder(songBinding, songListener);
        } else {
            LoadingItemBinding itemBinding = LoadingItemBinding.inflate(inflater, parent, false);
            return new LoadingViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlbumImageCardViewVH){
            imageCardViewVH.bindView((AlbumImageCardViewVH) holder, albumData);
        } else if (holder instanceof LabelVH && position == 1){
            labelVH.bindView((LabelVH) holder, SONG_LABEL);
        } else if (holder instanceof SongViewHolder){
            int songPosition = toSongPosition(position);
            SongData song = listSong.get(songPosition);
            ((SongViewHolder) holder).bindView (song, songPosition);
        }  else if (holder instanceof LabelVH && position == 2 + listSong.size() - 1 + 1){
            labelVH.bindView((LabelVH) holder, SIMILAR_LABEL);
        } else if (holder instanceof SimilarViewHolder){
            int similarPosition = toSimilarPosition(position);
            AlbumData album = listSimilarAlbum.get(similarPosition);
            ((SimilarViewHolder) holder).bindView(album, similarPosition);
        }
    }

    public int toSongPosition(int recyclerViewPosition) {
        return recyclerViewPosition - 2;
    }

    public int getRecyclerViewPositionFromSongPosition(int songPosition) {
        return songPosition + 2;
    }

    public int toSimilarPosition(int recyclerViewPosition) {
        return recyclerViewPosition - listSong.size() - 1 - 2;
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
            viewType = VIEW_TYPE_LABEL;
        } else if (position >= 2 && position <= lastSongPosition){
            viewType = VIEW_TYPE_DETAIL_SONG_ITEM;
        } else if (position == lastSongPosition + 1){
            viewType = VIEW_TYPE_LABEL;
        } else {
            viewType = VIEW_TYPE_DETAIL_SIMILAR_ITEM;
        }
        return viewType;
    }
}