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

import com.example.ayushyadav.ebuzz.Activities.MovieDetailActivity;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieResults;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesCardView1Adapter extends RecyclerView.Adapter<MoviesCardView1Adapter.ViewHolder> {

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<MovieResults> movieResults;
    Context context;
    onItemClickListener onItemClickListener;

    public MoviesCardView1Adapter(ArrayList<MovieResults> movieResults, Context context, onItemClickListener onItemClickListener) {
        this.movieResults = movieResults;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view_1, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MovieResults results = movieResults.get(position);
        holder.movieTitle.setText(results.getOriginTitle());
        Picasso.get().load(Constants.bigSizeImageURL + results.getBackdropPath()).into(holder.movieThumbnail);
        holder.rating.setText(results.getVotesAvg() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
        holder.movieThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle, rating;
        ImageView movieThumbnail;
        View itemView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            movieThumbnail = itemView.findViewById(R.id.movies_moviethumb);
            movieTitle = itemView.findViewById(R.id.movies_movietitle);
            rating = itemView.findViewById(R.id.movies_rating);
            cardView = itemView.findViewById(R.id.movie_card_view);
        }
    }

}
