package com.example.listaanimes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaanimes.Adapter.AnimeAdapter;
import com.example.listaanimes.Service.ApiService;
import com.example.listaanimes.Service.EpisodiosActivity;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AnimeAdapter.OnAnimeClickListener {

    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimeAdapter(this, this);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        loadAnimeList();
    }

    private void loadAnimeList() {
        Call<Anime> call = apiService.getAnimeList(1);
        call.enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Anime.Data> animeDataList = response.body().getData();
                    if (animeDataList != null) {
                        adapter.setAnimeList(animeDataList);
                    }
                } else {
                    Toast.makeText(MainActivity.this,
                            "Respuesta sin datos o no exitosa",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Error al cargar los datos: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAnimeClick(int animeId) {
        Intent intent = new Intent(this, EpisodiosActivity.class);
        intent.putExtra("animeId", animeId);
        startActivity(intent);
    }
}