package com.example.h071211083_finalmovie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {
    @SerializedName("results")
    private List<Tv> tvShows;

    public List<Tv> getTvShows() {
        return tvShows;
    }
}