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
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowResults;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TVShowsCardView2Adapter extends RecyclerView.Adapter<TVShowsCardView2Adapter.ViewHolder> {

    interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<TVShowResults> tvShowResults;
    Context context;
    TVShowsCardView1Adapter.onItemClickListener onItemClickListener;

    public TVShowsCardView2Adapter(ArrayList<TVShowResults> tvShowResults, Context context) {
        this.tvShowResults = tvShowResults;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_show_card_view_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TVShowResults results = tvShowResults.get(position);
        holder.tvShowTitle.setText(results.getTitle());
        Picasso.get().load(Constants.mediumSizeImageURL + results.getBackdropPath()).fit().into(holder.smallPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.TV_SHOW_ID, results.getId());
                context.startActivity(intent);
            }
        });
        holder.smallPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.TV_SHOW_ID, results.getId());
                context.startActivity(intent);
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
