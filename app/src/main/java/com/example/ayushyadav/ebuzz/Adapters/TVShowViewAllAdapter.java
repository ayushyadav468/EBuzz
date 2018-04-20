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
import com.example.ayushyadav.ebuzz.Activities.TVDetailActivity;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowResults;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TVShowViewAllAdapter extends RecyclerView.Adapter<TVShowViewAllAdapter.ViewHolder> {

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<TVShowResults> tvShowResults;
    Context mcontext;
    onItemClickListener listener;

    public TVShowViewAllAdapter(ArrayList<TVShowResults> tvShowResults, Context mcontext, onItemClickListener onItemClickListener) {
        this.tvShowResults = tvShowResults;
        this.mcontext = mcontext;
        this.listener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_card_view, parent, false);
        return new TVShowViewAllAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TVShowResults results = tvShowResults.get(position);
        holder.title.setText(results.getTitle());
        Picasso.get().load(Constants.mediumSizeImageURL + results.getPosterPath()).fit().into(holder.poster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        CardView cardView;
        ImageView poster;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cardView = itemView.findViewById(R.id.card_view);
            poster = itemView.findViewById(R.id.view_all_movie_thumb_small);
            title = itemView.findViewById(R.id.view_all_title_text_view);
        }
    }
}
