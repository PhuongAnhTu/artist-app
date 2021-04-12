package com.example.artist.adapter.thumbAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artist.adapter.viewholder.AlbumOfArtistVH;
import com.example.artist.adapter.viewholder.ArtistCardViewVH;
import com.example.artist.adapter.viewholder.LabelVH;
import com.example.artist.adapter.viewholder.LoadingViewHolder;
import com.example.artist.adapter.viewholder.SimilarViewHolder;
import com.example.artist.adapter.viewholder.SongViewHolder;
import com.example.artist.databinding.Dal1ImageAlbumCardviewBinding;
import com.example.artist.databinding.Dal2LabelTextBinding;
import com.example.artist.databinding.Dal3ItemSongBinding;
import com.example.artist.databinding.Dal4LabelSimilarBinding;
import com.example.artist.databinding.Dal5SimilarItemBinding;
import com.example.artist.databinding.LoadingItemBinding;
import com.example.artist.detailScreen.NewDetailAlbumFragment;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;
import com.example.artist.model.SongData;
import com.google.android.exoplayer2.ExoPlayer;

import java.util.ArrayList;
import java.util.List;

public class DetailArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArtistData artistData;
    private List<SongData> listSong = new ArrayList<>();
    public List<AlbumData> listAlbum = new ArrayList<>();
    public ArtistCardViewVH imageCardViewVH;
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

    public void setAlbumData(ArtistData artistData) {
        this.artistData = artistData;
        notifyDataSetChanged();
    }

    public void setAlbumOfArtist(List<AlbumData> list) {
        this.listAlbum = list;
        notifyDataSetChanged();
    }

    public void setListSongs(List<SongData> list) {
        this.listSong = list;
        notifyDataSetChanged();;
    }

    public void clear(){
        listSong.clear();
        listAlbum.clear();
        notifyDataSetChanged();
    }


    public DetailArtistAdapter(SongViewHolder.Listener songListener, ExoPlayer player, SimilarViewHolder.Listener similarListener){
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
            imageCardViewVH = new ArtistCardViewVH(binding);
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
        if (holder instanceof ArtistCardViewVH){
            imageCardViewVH.bindView((ArtistCardViewVH) holder, artistData);
        } else if (holder instanceof LabelVH && position == 1){
            labelVH.bindView((LabelVH) holder, ALBUM_LABEL);
        } else if (holder instanceof AlbumOfArtistVH){
            int albumPosition = toAlbumPosition(position);
            AlbumData album = listAlbum.get(albumPosition);
            ((AlbumOfArtistVH) holder).bindView (album, albumPosition);
        } else if (holder instanceof LabelVH && position == 2 + listSong.size() - 1 + 1){
            labelVH.bindView((LabelVH) holder, SONG_LABEL);
        } else if (holder instanceof SimilarViewHolder){
            int similarPosition = toSimilarPosition(position);
            AlbumData album = listAlbum.get(similarPosition);
            ((SimilarViewHolder) holder).bindView(album, similarPosition);
        }
    }

    public int toAlbumPosition(int recyclerViewPosition) {
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
        return 1 + 1 + listSong.size() + 1 + listAlbum.size();
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