package com.example.ayushyadav.ebuzz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayushyadav.ebuzz.Activities.DetailActivity;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieResults;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesCardView2Adapter extends RecyclerView.Adapter<MoviesCardView2Adapter.ViewHolder> {

    interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<MovieResults> movieResults;
    Context context;
    onItemClickListener listener;

    public MoviesCardView2Adapter(ArrayList<MovieResults> movieResults, Context context) {
        this.movieResults = movieResults;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view_2, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesCardView2Adapter.ViewHolder holder, int position) {
        final MovieResults results = movieResults.get(position);
        holder.movieTitle.setText(results.getOriginTitle());
        Picasso.get().load(Constants.mediumSizeImageURL + results.getBackdropPath()).fit().into(holder.smallPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // listener.onItemClick(holder.getAdapterPosition());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.MOVIE_ID, results.getId());
                context.startActivity(intent);
            }
        });
        holder.smallPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.MOVIE_ID, results.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView smallPoster;
        TextView movieTitle;
        View itemView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cardView = itemView.findViewById(R.id.movie_card_view);
            smallPoster = itemView.findViewById(R.id.movie_movieThumbsmall);
            movieTitle = itemView.findViewById(R.id.movie_text_view_title_show_card);
        }
    }
}
