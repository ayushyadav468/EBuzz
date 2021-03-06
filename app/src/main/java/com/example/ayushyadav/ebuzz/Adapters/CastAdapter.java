package com.example.ayushyadav.ebuzz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ayushyadav.ebuzz.Activities.CastDetailActivity;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.CastResults;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    ArrayList<CastResults> castResults;
    Context context;
    onItemClickListener onItemClickListener;

    public CastAdapter(ArrayList<CastResults> castResults, Context context, onItemClickListener onItemClickListener) {
        this.castResults = castResults;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_fragment_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CastResults results = castResults.get(position);
        holder.nameTextView.setText(results.getName());
        Picasso.get().load(Constants.bigSizeImageURL + results.getProfilePath()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
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
        CircleImageView imageView;
        TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.profile_image);
            nameTextView = itemView.findViewById(R.id.cast_textView);
        }
    }
}
