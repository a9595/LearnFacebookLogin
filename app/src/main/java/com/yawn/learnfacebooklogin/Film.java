package com.yawn.learnfacebooklogin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieorange on 18/08/16.
 */
public class Film {
  public String pictureUrl;
  public String name;
  public String genre;

  public Film() {
    pictureUrl = "https://upload.wikimedia.org/wikipedia/en/4/46/Deadpool_poster.jpg";
    genre = "Action&Adventure";
  }

  public Film(String name) {
    this();
    this.name = name;
  }

  public static List<Film> getDummy(int count) {
    List<Film> films = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      films.add(new Film("Deadpool"));
    }
    return films;
  }
}
