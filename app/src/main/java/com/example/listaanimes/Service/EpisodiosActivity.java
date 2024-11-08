package com.example.listaanimes.Service;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaanimes.Adapter.EpisodiosAdapter;
import com.example.listaanimes.Episodios;
import com.example.listaanimes.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EpisodiosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApiService apiService;
    private EpisodiosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodios); // Cambiado de item_anime a activity_episodios

        int animeId = getIntent().getIntExtra("animeId", -1);
        if (animeId == -1) {
            finish();
            return;
        }

        recyclerView = findViewById(R.id.episodioRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador
        adapter = new EpisodiosAdapter(this);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        loadEpisodes(animeId);
    }

    private void loadEpisodes(int animeId) {
        apiService.getEpisodes(animeId).enqueue(new Callback<Episodios>() {
            @Override
            public void onResponse(Call<Episodios> call, Response<Episodios> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setEpisodiosList(response.body().data);
                } else {
                    Toast.makeText(EpisodiosActivity.this,
                            "Error al cargar los episodios",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Episodios> call, Throwable t) {
                Toast.makeText(EpisodiosActivity.this,
                        "Error al cargar los episodios: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

