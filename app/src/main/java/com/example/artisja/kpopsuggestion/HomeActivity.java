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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

public class HomeActivity extends AppCompatActivity {

    TextView titleTextView;
    public ArrayList<SongInfo> songInfoArrayList;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static Context context;
    public DatabaseReference firebaseDatabase;
    public HomeListAdapter homeListAdapter;
    SongInfo songInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this.getApplicationContext();
        songInfoArrayList = new ArrayList<SongInfo>();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    SongInfo post = postSnapshot.getValue(SongInfo.class);
                    songInfoArrayList.add(post);
                }
                homeListAdapter = new HomeListAdapter(songInfoArrayList);
                recyclerView.setAdapter(homeListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        SecureRandom secureRandom = new SecureRandom();
        titleTextView = (TextView) findViewById(R.id.home_title_text_view);
        recyclerView = (RecyclerView) findViewById(R.id.song_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddSongActivity.class);
                startActivity(intent);
            }
        });
    }


}
