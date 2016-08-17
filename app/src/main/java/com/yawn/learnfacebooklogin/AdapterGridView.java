package com.yawn.learnfacebooklogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by tieorange on 18/08/16.
 */
public class AdapterGridView extends RecyclerView.Adapter<AdapterGridView.ViewHolder> {

  private final Context mContext;
  public final List<Film> mFilms;

  public AdapterGridView(Context context, List<Film> films) {
    mContext = context;
    mFilms = films;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_grid, null);
    ViewHolder viewHolder = new ViewHolder(layoutView);
    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Film film = mFilms.get(position);
    holder.name.setText(film.name);
    Glide.with(mContext).load(film.pictureUrl).into(holder.image);
  }

  @Override public int getItemCount() {
    return mFilms.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.image) public ImageView image;
    @BindView(R.id.name) public TextView name;
    @BindView(R.id.genre) TextView genre;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
      int adapterPosition = getAdapterPosition();
      Film film = mFilms.get(adapterPosition);
      Intent intent = FilmActivity.buildIntent(mContext, film);
      mContext.startActivity(intent);
    }
  }
}
