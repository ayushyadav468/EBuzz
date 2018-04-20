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

public class TVShowsCardView2Adapter extends RecyclerView.Adapter<TVShowsCardView2Adapter.ViewHolder> {

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<TVShowResults> tvShowResults;
    Context context;
    onItemClickListener onItemClickListener;

    public TVShowsCardView2Adapter(ArrayList<TVShowResults> tvShowResults, Context context, onItemClickListener onItemClickListener) {
        this.tvShowResults = tvShowResults;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_show_card_view_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        final TVShowResults results = tvShowResults.get(position);
        holder.tvShowTitle.setText(results.getTitle());
        Picasso.get().load(Constants.mediumSizeImageURL + results.getBackdropPath()).fit().into(holder.smallPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
        holder.smallPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView smallPoster;
        TextView tvShowTitle;
        View itemView;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cardView = itemView.findViewById(R.id.tv_show_card_view);
            smallPoster = itemView.findViewById(R.id.tv_show_movieThumbsmall);
            tvShowTitle = itemView.findViewById(R.id.tv_show_text_view_title_show_card);
        }
    }
}
