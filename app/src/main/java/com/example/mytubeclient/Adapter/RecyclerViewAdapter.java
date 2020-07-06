package com.example.mytubeclient.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mytubeclient.Model.Upload;
import com.example.mytubeclient.R;
import com.example.mytubeclient.SongActivity;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<Upload> thumbnails;

    public RecyclerViewAdapter(Context context, List<Upload> thumbnails) {
        this.context = context;
        this.thumbnails = thumbnails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Upload upload = thumbnails.get(position);
        holder.tv_title.setText(upload.getName());

        Glide.with(context).load(upload.getUrl()).into(holder.image_thumb);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("songsCategory", upload.getSongsCategory());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thumbnails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        ImageView image_thumb;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.txtTitle);
            image_thumb = itemView.findViewById(R.id.card_image);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
