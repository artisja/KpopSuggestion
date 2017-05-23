package com.example.artisja.kpopsuggestion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

public class HomeActivity extends AppCompatActivity {

    TextView titleTextView;
    ArrayList<SongInfo> songInfoArrayList;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static  Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this.getApplicationContext();
        setUpData();
        setViews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddSongActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpData() {
        SongInfo songInfo = new SongInfo("Sorry","Zion.T","OO",
        "http://images.kpopstarz.com/data/thumbs/full/503195/600/0/0/0/zion-ts-teaser-photo-for-album-oo-posted-in-his-instagram.jpg");
        songInfoArrayList = new ArrayList<SongInfo>();
        songInfoArrayList.add(songInfo);
    }

    private void setViews() {
        titleTextView = (TextView) findViewById(R.id.home_title_text_view);
        recyclerView = (RecyclerView) findViewById(R.id.song_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeListAdapter homeListAdapter = new HomeListAdapter(songInfoArrayList);
        recyclerView.setAdapter(homeListAdapter);
    }
}
