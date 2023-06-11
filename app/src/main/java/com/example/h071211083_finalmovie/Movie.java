package com.example.h071211083_finalmovie;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("genre_id")
    private final int id;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("release_date")
    private final String releaseDate;

    @SerializedName("title")
    private final String title;

    @SerializedName("vote_average")
    private final Double voteAverage;

    @SerializedName("backdrop_path")
    private final String backdropUrl;

    public Movie(int id, String overview, String posterPath, String releaseDate, String title, Double voteAverage, String backdropUrl) {
        this.id = id;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteAverage = voteAverage;
        this.backdropUrl = backdropUrl;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        overview = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        backdropUrl = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeString(title);
        if (voteAverage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(voteAverage);
        }
        parcel.writeString(backdropUrl);
    }


}