package com.example.artisja.kpopsuggestion;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static android.R.attr.id;

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

    public ArrayList<SongInfo> homeList;
    public FirebaseStorage firebaseStorage;
    public StorageReference storageReference;

    public HomeListAdapter(ArrayList<SongInfo> songs){
        homeList = songs;
        homeList.size();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://kpopsuggestionapp.appspot.com/");
    }

    @Override
    public SongListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_row,parent,false);
        SongListViewHolder songListViewHolder = new SongListViewHolder(v);
        return songListViewHolder;
    }

    @Override
    public void onBindViewHolder(final SongListViewHolder holder, final int position) {
        holder.songNameText.setText(homeList.get(position).getSong());
        holder.albumText.setText(homeList.get(position).getAlbum());
        holder.singerText.setText(homeList.get(position).getArtist());
        if (homeList.get(position).isURL()){
            Glide.with(holder.albumImage.getContext())
                .load(homeList.get(position).getImageURL()).fitCenter()
                .into(holder.albumImage);
        }else {
            StorageReference ref = storageReference.child(homeList.get(position).getImageURL());
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(holder.albumImage.getContext())
                            .load(uri.toString()).centerCrop().fitCenter()
                            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .into(holder.albumImage);
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(holder.albumImage.getContext(), "Failure downloading picture for song: " + homeList.get(position).getSong(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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
