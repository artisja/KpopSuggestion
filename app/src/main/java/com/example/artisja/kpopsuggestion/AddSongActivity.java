package com.example.artisja.kpopsuggestion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;


public class AddSongActivity extends AppCompatActivity {

    InputRow nameRow,artistRow,albumRow,urlRow;
    Button submitButton;
    private static final String SHOWCASE_ID = "995";
    public DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        setUpDatabase();
        setUpViews();
        Double testIDs = Math.random()*100;
        int color = Color.argb(96,1751,47,173);
        new MaterialIntroView.Builder(this)
                .enableDotAnimation(false)
                .enableIcon(false)
                .setMaskColor(color).dismissOnTouch(true)
                .enableFadeAnimation(true)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.NORMAL)
                .setShape(ShapeType.RECTANGLE)
                .setInfoText(getResources().getString(R.string.add_song_prompt))
                .setTarget(submitButton)
                .setUsageId(String.valueOf(testIDs))
                .performClick(true)
                .show();

    }

    private void setUpDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void setUpViews() {
        nameRow = (InputRow) findViewById(R.id.name_row);
        nameRow.setLabel("Name of Song: ");
        nameRow.setHint("Song");
        artistRow = (InputRow) findViewById(R.id.artist_row);
        artistRow.setLabel("Name of Artist: ");
        artistRow.setHint("Artist");
        albumRow = (InputRow) findViewById(R.id.album_row);
        albumRow.setLabel("Name of Album: ");
        albumRow.setHint("Album");
        urlRow = (InputRow) findViewById(R.id.url_row);
        urlRow.setLabel("Url Link: ");
        urlRow.setHint("URL");
        submitButton = (Button) findViewById(R.id.submit_song);
        final Intent intent = new Intent(this,HomeActivity.class);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecureRandom secureRandom = new SecureRandom();
                int id = secureRandom.nextInt();
                SongInfo song = new SongInfo(nameRow.getInputEdit(),artistRow.getInputEdit(),albumRow.getInputEdit(),urlRow.getInputEdit());
                firebaseDatabase.child(String.valueOf(id)).setValue(song);
                startActivity(intent);
                Toast.makeText(AddSongActivity.this, "Added to Google Firebase", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
