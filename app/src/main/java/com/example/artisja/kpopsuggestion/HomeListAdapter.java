package com.example.artisja.kpopsuggestion;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by artisja on 5/20/17.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.SongListViewHolder> {

    public static class SongListViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView singerText,albumText,songNameText;
        ImageView albumImage;
        public SongListViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.info_block);
            singerText = (TextView) itemView.findViewById(R.id.song_artist_rv);
            albumText = (TextView) itemView.findViewById(R.id.song_album_rv);
            songNameText = (TextView) itemView.findViewById(R.id.song_name_rv);
            albumImage = (ImageView) itemView.findViewById(R.id.song_image_view_rv);
        }
    }

    private ArrayList<SongInfo> homeList;

    public HomeListAdapter(ArrayList<SongInfo> songs){
        homeList = songs;
    }

    @Override
    public SongListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_row,parent,false);
        SongListViewHolder songListViewHolder = new SongListViewHolder(v);
        return songListViewHolder;
    }

    @Override
    public void onBindViewHolder(SongListViewHolder holder, int position) {
        holder.songNameText.setText(homeList.get(position).getSong());
        holder.albumText.setText(homeList.get(position).getAlbum());
        holder.singerText.setText(homeList.get(position).getArtist());
        Glide.with(holder.albumImage.getContext())
                .load(homeList.get(position).getImageURL()).fitCenter()
                .into(holder.albumImage);
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
