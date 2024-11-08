package com.example.listaanimes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.listaanimes.Anime;
import com.example.listaanimes.R;

import java.util.ArrayList;
import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {
    private List<Anime.Data> animeList;
    private Context context;
    private OnAnimeClickListener listener;

    public interface OnAnimeClickListener {
        void onAnimeClick(int animeId);
    }

    public AnimeAdapter(Context context, OnAnimeClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.animeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anime.Data anime = animeList.get(position);

        holder.titleTextView.setText(anime.title);
        holder.synopsisTextView.setText(anime.synopsis);
        holder.episodesTextView.setText("Episodios: " + anime.episodes);

        Glide.with(context)
                .load(anime.images.jpg.image_url)
                .into(holder.imagenView);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAnimeClick(anime.mal_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public void setAnimeList(List<Anime.Data> animeList) {
        this.animeList = animeList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenView;
        TextView synopsisTextView;
        TextView titleTextView;
        TextView episodesTextView;

        ViewHolder(View itemView) {
            super(itemView);
            imagenView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            synopsisTextView = itemView.findViewById(R.id.synopsisTextView);
            episodesTextView = itemView.findViewById(R.id.episodioRecyclerView);
        }
    }
}