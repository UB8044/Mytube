package com.example.mytubeclient.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytubeclient.Model.GetSongs;
import com.example.mytubeclient.Model.Utility;
import com.example.mytubeclient.R;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongAdapterViewHolder>{

    Context context;
    List<GetSongs> arrayListSongs;
    private RecyclerItemClickListener listener;
    private int selectedPositon;

    public SongAdapter(Context context, List<GetSongs> arrayListSongs, RecyclerItemClickListener listener) {
        this.context = context;
        this.arrayListSongs = arrayListSongs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songs_list, parent, false);
        return new SongAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapterViewHolder holder, int position) {
        GetSongs getSongs = arrayListSongs.get(position);

        if (getSongs != null){
            if (selectedPositon == position){
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                holder.img_play.setVisibility(View.VISIBLE);
            } else {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                holder.img_play.setVisibility(View.INVISIBLE);
            }
        }

        holder.tvTitle.setText(getSongs.getSongTitle());

        holder.bind(getSongs, listener);
    }

    @Override
    public int getItemCount() {
        return arrayListSongs.size();
    }

    public class SongAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        ImageView img_play;

        public SongAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            img_play = itemView.findViewById(R.id.btn_play);
        }

        public void bind(GetSongs getSongs, final RecyclerItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickListener(getSongs, getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListener {
        void onClickListener(GetSongs songs, int position);
    }

    public int getSelectedPositon(){
        return selectedPositon;
    }

    public void setSelectedPositon(int selectedPositon){
        this.selectedPositon = selectedPositon;
    }
}
