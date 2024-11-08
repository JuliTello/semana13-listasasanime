package com.example.listaanimes.Service;

import com.example.listaanimes.Adapter.AnimeAdapter;
import com.example.listaanimes.Anime;
import com.example.listaanimes.Episodios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("anime")
    Call<Anime> getAnimeList(@Query("page") int page);

    @GET("anime/{id}/episodes")
    Call<Episodios> getEpisodes(@Path("id") int animeId);
}
