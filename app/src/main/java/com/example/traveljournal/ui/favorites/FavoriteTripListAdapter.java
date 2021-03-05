package com.example.traveljournal.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.room.Trip;

import java.util.List;

public class FavoriteTripListAdapter extends RecyclerView.Adapter<FavoriteTripListAdapter.FavoriteTripViewHolder> {
    class FavoriteTripViewHolder extends RecyclerView.ViewHolder {

        private final TextView tripItemView;
        private final TextView destinationItemView;
        private final TextView priceItemView;
        private final ImageButton bookmark;
        private final RatingBar ratingBar;
        public boolean isFavourite;

        private FavoriteTripViewHolder(View itemView) {
            super(itemView);
            tripItemView = itemView.findViewById(R.id.textViewTripName);
            destinationItemView = itemView.findViewById(R.id.textViewDestination);
            priceItemView = itemView.findViewById(R.id.textViewPrice);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            bookmark = itemView.findViewById(R.id.buttonBookmark);
        }
    }

    private final LayoutInflater mInflater;
    private List<Trip> mTrips; // Cached copy of trips

    public FavoriteTripListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavoriteTripListAdapter.FavoriteTripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.trip_item, parent, false);
        return new FavoriteTripListAdapter.FavoriteTripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteTripListAdapter.FavoriteTripViewHolder holder, int position) {
        if (mTrips != null) {
            Trip current = mTrips.get(position);
            holder.tripItemView.setText(current.getTrip());
            holder.destinationItemView.setText(current.getDestination());
            holder.priceItemView.setText(current.getPrice() + " EUR");
            holder.ratingBar.setRating(current.getRating());
            holder.isFavourite = current.getIsFavourite();
            if (current.getIsFavourite() == false)
                holder.bookmark.setImageLevel(0);
            else
                holder.bookmark.setImageLevel(1);
        } else {
            holder.tripItemView.setText(R.string.no_trip);
            holder.destinationItemView.setText(R.string.no_destination);
            holder.priceItemView.setText(R.string.no_data);
            holder.ratingBar.setRating(0);
            holder.isFavourite = false;
        }
    }

    public void setTrips(List<Trip> trips) {
        mTrips = trips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTrips != null)
            return mTrips.size();
        else return 0;
    }
}
