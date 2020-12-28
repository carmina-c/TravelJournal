package com.example.traveljournal.trip_recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.MainActivity;
import com.example.traveljournal.R;
import com.example.traveljournal.ui.home.HomeFragment;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private List<Trip> trips;
    private Context context;

    public TripAdapter(List<Trip> trips, Context context) {
        this.trips = trips;
        this.context = context;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getTextViewTripName().setText(currentTrip.getTripName());
        holder.getTextViewPrice().setText(currentTrip.getPrice());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
