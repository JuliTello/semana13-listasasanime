package com.example.listaanimes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaanimes.Episodios;
import com.example.listaanimes.R;

import java.util.ArrayList;
import java.util.List;

public class EpisodiosAdapter extends RecyclerView.Adapter<EpisodiosAdapter.ViewHolder> {
        private List<Episodios.EpisodioData> episodiosList;
        private Context context;

        public EpisodiosAdapter(Context context) {
            this.context = context;
            this.episodiosList = new ArrayList<>();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_episodios, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Episodios.EpisodioData episodio = episodiosList.get(position);

            holder.episodeNumberTextView.setText("Episodio " + (position + 1));
            holder.episodeTitleTextView.setText(episodio.title);
            holder.episodeAiredTextView.setText("Emitido: " + episodio.aired);
        }

        @Override
        public int getItemCount() {
            return episodiosList.size();
        }

        public void setEpisodiosList(List<Episodios.EpisodioData> episodiosList) {
            this.episodiosList = episodiosList;
            notifyDataSetChanged();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView episodeNumberTextView;
            TextView episodeTitleTextView;
            TextView episodeAiredTextView;

            ViewHolder(View itemView) {
                super(itemView);
                episodeNumberTextView = itemView.findViewById(R.id.episodioNumTextView);
                episodeTitleTextView = itemView.findViewById(R.id.episodioTitulo);
                episodeAiredTextView = itemView.findViewById(R.id.episodioRecyclerView);
            }
        }
}
