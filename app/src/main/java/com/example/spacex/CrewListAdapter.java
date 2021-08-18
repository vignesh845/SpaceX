package com.example.spacex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CrewListAdapter extends RecyclerView.Adapter<CrewListAdapter.ViewHolder> {

    private List<CrewModel> crewModels = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_list_item, parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CrewModel crewModel = crewModels.get(position);
        Glide.with(holder.imageViewPicture.getContext())
                .load(crewModel.getImage())
                .into(holder.imageViewPicture);
        holder.textViewName.setText(crewModel.getName());
        holder.textViewAgency.setText(crewModel.getAgency());
        holder.textViewStatus.setText(crewModel.getStatus());
        holder.textViewWikipedia.setText(crewModel.getWikipedia());
    }

    @Override
    public int getItemCount() {
        return crewModels.size();
    }

    public void setCrewModels(List<CrewModel> crewModels) {
        this.crewModels = crewModels;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPicture;
        private final TextView textViewName;
        private final TextView textViewAgency;
        private final TextView textViewStatus;
        private final TextView textViewWikipedia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPicture = itemView.findViewById(R.id.image_view_picture);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewAgency = itemView.findViewById(R.id.text_view_agency);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
            textViewWikipedia = itemView.findViewById(R.id.text_view_wikipedia);
        }
    }
}
