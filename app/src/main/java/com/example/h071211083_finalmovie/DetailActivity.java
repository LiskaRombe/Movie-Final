package com.example.h071211083_finalmovie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ImageView latar, back, favorit, poster;
    private TextView judul, tahun, rating, deskripsi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        latar = findViewById(R.id.latar_belakang);
        back = findViewById(R.id.back);
        favorit = findViewById(R.id.love);
        poster = findViewById(R.id.poster);
        judul = findViewById(R.id.judul);
        tahun = findViewById(R.id.date);
        rating = findViewById(R.id.rating);
        deskripsi = findViewById(R.id.sinopsis);
        dbHelper = new DatabaseHelper(this);

        back.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            Movie movie = intent.getParcelableExtra("movie");
            String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + movie.getBackdropUrl();
            judul.setText(movie.getTitle());
            tahun.setText(movie.getReleaseDate());
            rating.setText(movie.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(poster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(latar);
            deskripsi.setText(movie.getOverview());

            if (dbHelper.isFavorites(movie.getTitle())) {
                favorit.setImageResource(R.drawable.light_favorite);
            }

            favorit.setOnClickListener(view ->  {
                if (!dbHelper.isFavorites(movie.getTitle())) {
                    favorit.setImageResource(R.drawable.light_favorite);
                    addToFavorites(movie.getId(), movie.getOverview(), posterUrl, movie.getReleaseDate(), movie.getTitle(), movie.getVoteAverage(), backdropUrl);
                } else {
                    favorit.setImageResource(R.drawable.favorite);
                    deleteFromFavorites(movie.getTitle());
                }
            });
        } else if (intent.getParcelableExtra("show") != null) {
            Tv show = intent.getParcelableExtra("show");
            String posterUrl = "https://image.tmdb.org/t/p/w500" + show.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + show.getBackdropUrl();
            judul.setText(show.getTitle());
            tahun.setText(show.getFirstAirDate());
            rating.setText(show.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(poster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(latar);
            deskripsi.setText(show.getOverview());

            if (dbHelper.isFavorites(show.getTitle())) {
                favorit.setImageResource(R.drawable.light_favorite);
            }

            favorit.setOnClickListener(view -> {
                if (!dbHelper.isFavorites(show.getTitle())) {
                    favorit.setImageResource(R.drawable.light_favorite);
                    addToFavorites(show.getId(), show.getOverview(), posterUrl, show.getTitle(), show.getTitle(), show.getVoteAverage(), backdropUrl);
                } else {
                    favorit.setImageResource(R.drawable.favorite);
                    deleteFromFavorites(show.getTitle());
                }
            });
        }
        else {
            Favorite favorite = intent.getParcelableExtra("favorite");
            String posterUrl = "https://image.tmdb.org/t/p/w500" + favorite.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + favorite.getBackdropUrl();
            judul.setText(favorite.getTitle());
            tahun.setText(favorite.getReleaseDate());
            rating.setText(favorite.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(poster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(latar);
            deskripsi.setText(favorite.getOverview());

            if (dbHelper.isFavorites(favorite.getTitle())) {
                favorit.setImageResource(R.drawable.light_favorite);
            }

            favorit.setOnClickListener(view -> {
                if (!dbHelper.isFavorites(favorite.getTitle())) {
                    favorit.setImageResource(R.drawable.light_favorite);
                    addToFavorites(favorite.getId(), favorite.getOverview(), posterUrl, favorite.getTitle(), favorite.getTitle(), favorite.getVoteAverage(), backdropUrl);
                } else {
                    favorit.setImageResource(R.drawable.favorite);
                    deleteFromFavorites(favorite.getTitle());
                }
            });
        }
    }

    private void addToFavorites(int id, String overview, String posterUrl, String releaseDate, String title, double voteAverage, String backdropUrl) {
        Favorite favorite = new Favorite(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
        long result = dbHelper.insertFavorite(favorite);
        if (result != -1) {
            Toast.makeText(this, "Movie / Show added to favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add to favorite", Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteFromFavorites(String nama) {
        long result = dbHelper.deleteFavorite(nama);
        if (result != -1) {
            Toast.makeText(this, "Movie / Show Deleted From favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete movie", Toast.LENGTH_SHORT).show();
        }
    }
}

