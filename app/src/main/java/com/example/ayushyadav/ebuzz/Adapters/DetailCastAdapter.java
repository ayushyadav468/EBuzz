package com.example.ayushyadav.ebuzz.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.CastResults;
import com.example.ayushyadav.ebuzz.NetworkClasses.DetailActivityCastResults;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailCastAdapter extends RecyclerView.Adapter<DetailCastAdapter.ViewHolder> {

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<DetailActivityCastResults> castResults;
    Context context;
    onItemClickListener onItemClickListener;

    public DetailCastAdapter (ArrayList<DetailActivityCastResults> castResults, Context context, onItemClickListener onItemClickListener) {
        this.castResults = castResults;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_cast_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DetailActivityCastResults results = castResults.get(position);
        holder.nameTextView.setText(results.getName());
        Picasso.get().load(Constants.bigSizeImageURL + results.getImage()).into(holder.imageView);
        holder.characterTextView.setText(results.getCharater());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageView;
        TextView nameTextView;
        TextView characterTextView;
        CardView detailCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            detailCardView = itemView.findViewById(R.id.detail_cast_card_view);
            imageView = itemView.findViewById(R.id.detail_profile_image);
            characterTextView = itemView.findViewById(R.id.characterTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
