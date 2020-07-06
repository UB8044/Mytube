package com.example.mytubeclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mytubeclient.Adapter.RecyclerViewAdapter;
import com.example.mytubeclient.Model.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    DatabaseReference db;
    ProgressDialog progressDialog;
    private List<Upload> thumbnails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        progressDialog = new ProgressDialog(this);
        thumbnails = new ArrayList<>();
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        db = FirebaseDatabase.getInstance().getReference("thumbnails");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                for (DataSnapshot postsnapshot : snapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    thumbnails.add(upload);
                }
                adapter = new RecyclerViewAdapter(getApplicationContext(), thumbnails);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }
}
