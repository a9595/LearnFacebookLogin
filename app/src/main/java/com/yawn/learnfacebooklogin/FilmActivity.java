package com.yawn.learnfacebooklogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import org.parceler.Parcels;

public class FilmActivity extends AppCompatActivity {

  private static final String EXTRA_FILM = "film";
  private Film mFilm;
  @BindView(R.id.filmName) TextView mFilmName;
  @BindView(R.id.filmImage) ImageView mFilmImage;

  public static Intent buildIntent(Context context, Film film) {
    Intent intent = new Intent(context, FilmActivity.class);
    intent.putExtra(EXTRA_FILM, Parcels.wrap(film));
    return intent;
  }

  public void extractExtras() {
    mFilm = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_FILM));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_film);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    extractExtras();
    initViews();
  }

  private void initViews() {
    setTitle(mFilm.name);
    mFilmName.setText(mFilm.name);
    Glide.with(getApplicationContext()).load(mFilm.pictureUrl).into(mFilmImage);
  }
}
