package com.example.listaanimes;

import java.util.List;

public class Episodios {
    public List<EpisodioData> data;

    public static class EpisodioData {
        public int id;
        public String title;
        public String aired;
    }
}
