package com.example.mytubeclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mytubeclient.Adapter.VideoAdapter;
import com.example.mytubeclient.Model.GetVideos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VideoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("video");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<GetVideos, VideoAdapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<GetVideos, VideoAdapter>(GetVideos.class, R.layout.videos_list, VideoAdapter.class, reference) {
            @Override
            protected void populateViewHolder(VideoAdapter videoAdapter, GetVideos getVideos, int i) {
                videoAdapter.setVideo(getApplication(), getVideos.getTitle(), getVideos.getUrl());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
