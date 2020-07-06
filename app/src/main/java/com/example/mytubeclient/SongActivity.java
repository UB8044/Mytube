package com.example.mytubeclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.mytubeclient.Adapter.SongAdapter;
import com.example.mytubeclient.Model.GetSongs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Boolean checkin = false;
    List<GetSongs> upload;
    SongAdapter adapter;
    DatabaseReference db;
    ValueEventListener valueEventListener;
    JcPlayerView jcPlayerView;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();
    private int curentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        jcPlayerView = findViewById(R.id.jcPlayer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        upload = new ArrayList<>();
        recyclerView.setAdapter(adapter);
        adapter = new SongAdapter(getApplicationContext(), upload, new SongAdapter.RecyclerItemClickListener() {
            @Override
            public void onClickListener(GetSongs songs, int position) {
                changeSelectedSong(position);

                jcPlayerView.playAudio(jcAudios.get(position));
                jcPlayerView.setVisibility(View.VISIBLE);
                jcPlayerView.createNotification();
            }
        });

        db = FirebaseDatabase.getInstance().getReference("songs");
        valueEventListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                upload.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GetSongs getSongs = dataSnapshot.getValue(GetSongs.class);
                    getSongs.setKey(dataSnapshot.getKey());
                    curentIndex = 0;
                final String s = getIntent().getExtras().getString("songsCategory");
                    if (s.equals((getSongs.getSongsCategory()))){
                        upload.add(getSongs);
                        checkin = true;
                        jcAudios.add(JcAudio.createFromURL(getSongs.getSongTitle(), getSongs.getSongLink()));
                    }
                }
                adapter.setSelectedPositon(0);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                if (checkin){
                    jcPlayerView.initPlaylist(jcAudios, null);
                } else {
                    Toast.makeText(SongActivity.this, "song not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    public void changeSelectedSong (int index){
        adapter.notifyItemChanged(adapter.getSelectedPositon());
        curentIndex = index;
        adapter.setSelectedPositon(curentIndex);
        adapter.notifyItemChanged(curentIndex);
    }
}
