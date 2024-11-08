package com.example.listaanimes;

import java.util.List;

public class Anime {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
            public int mal_id;
            public String title;
            public Images images;
            public String aired_string;
            public String synopsis;
            public int episodes;

            public static class Images {
                public Jpg jpg;

                public static class Jpg {
                    public String image_url;
                }
        }

        public int getMal_id() {
            return mal_id;
        }

        public void setMal_id(int mal_id) {
            this.mal_id = mal_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public String getAired_string() {
            return aired_string;
        }

        public void setAired_string(String aired_string) {
            this.aired_string = aired_string;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public int getEpisodes() {
            return episodes;
        }

        public void setEpisodes(int episodes) {
            this.episodes = episodes;
        }
    }
}