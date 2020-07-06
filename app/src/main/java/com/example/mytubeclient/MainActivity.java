package com.example.mytubeclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mytubeclient.Adapter.RecyclerViewAdapter;
import com.example.mytubeclient.Adapter.VideoAdapter;
import com.example.mytubeclient.Model.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openListMusik(View v){
        Intent in = new Intent(MainActivity.this, MainMenu.class);
        startActivity(in);
    }

    public void openListVideo(View v){
        Intent in = new Intent(MainActivity.this, VideoActivity.class);
        startActivity(in);
    }
}
