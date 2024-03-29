package com.example.traveljournal.room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.NewTripActivity;
import com.example.traveljournal.R;
import com.example.traveljournal.TripDetailsActivity;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {
    class TripViewHolder extends RecyclerView.ViewHolder {
        public static final int EDIT_TRIP_ACTIVITY_REQUEST_CODE = 2;

        private final ConstraintLayout root;
        protected CardView cardViewTripItem;
        private final TextView tripItemView;
        private final TextView destinationItemView;
        private final TextView priceItemView;
        private final ImageButton bookmark;
        private final RatingBar ratingBar;
        private float ratingBarValue = 0;
        public boolean isFavourite;
        private String startDate, endDate;
        private int id = 0;

        private TripViewHolder(View itemView) {
            super(itemView);

            System.out.println("CONTEXT: " + itemView);

            //Context context = itemView.findViewById(R.id.cardViewTripItem).getContext();

            Context context = itemView.getContext();
            root = itemView.findViewById(R.id.constraintLayoutTripItem);
            //cardViewTripItem = itemView.findViewById(R.id.cardViewTripItem);
            tripItemView = itemView.findViewById(R.id.textViewTripName);
            destinationItemView = itemView.findViewById(R.id.textViewDestination);
            priceItemView = itemView.findViewById(R.id.textViewPrice);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            bookmark = itemView.findViewById(R.id.buttonBookmark);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View itemView) {
                    Intent activity = new Intent(context, NewTripActivity.class);
                    Bundle dataDetails = new Bundle();
                    dataDetails.putString("TripName", tripItemView.getText().toString());
                    dataDetails.putString("Destination", destinationItemView.getText().toString());
                    dataDetails.putString("Price", priceItemView.getText().toString());
                    ratingBarValue = ratingBar.getRating();
                    dataDetails.putFloat("Rating", ratingBarValue);
                    dataDetails.putString("StartDate", startDate);
                    dataDetails.putString("EndDate", endDate);
                    dataDetails.putBoolean("Bookmark", isFavourite);
                    dataDetails.putInt("ID", id);
                    activity.putExtras(dataDetails);
                    //context.startActivity(activity);
                    ((Activity) context).startActivityForResult(activity, EDIT_TRIP_ACTIVITY_REQUEST_CODE);
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activity = new Intent(context, TripDetailsActivity.class);
                    Bundle dataDetails = new Bundle();
                    dataDetails.putString("TripName", tripItemView.getText().toString());
                    dataDetails.putString("Destination", destinationItemView.getText().toString());
                    ratingBarValue = ratingBar.getRating();
                    dataDetails.putFloat("Rating", ratingBarValue);
                    dataDetails.putString("StartDate", startDate);
                    dataDetails.putString("EndDate", endDate);
                    dataDetails.putBoolean("Bookmark", isFavourite);
                    activity.putExtras(dataDetails);
                    context.startActivity(activity);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Trip> mTrips; // Cached copy of trips

    public TripListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        if (mTrips != null) {
            Trip current = mTrips.get(position);
            holder.id = current.getID();
            holder.tripItemView.setText(current.getTrip());
            holder.destinationItemView.setText(current.getDestination());
            holder.priceItemView.setText(current.getPrice() + " EUR");
            holder.ratingBar.setRating(current.getRating());
            holder.isFavourite = current.getIsFavourite();
            if (current.getIsFavourite() == false)
                holder.bookmark.setImageLevel(0);
            else
                holder.bookmark.setImageLevel(1);
            holder.startDate = current.getStartDate();
            holder.endDate = current.getEndDate();
        } else {
            // Covers the case of data not being ready yet.
            holder.tripItemView.setText(R.string.no_trip);
            holder.destinationItemView.setText(R.string.no_destination);
            holder.priceItemView.setText(R.string.no_data);
            holder.ratingBar.setRating(0);
            holder.isFavourite = false;
            holder.startDate = "Unknown";
            holder.endDate = "Unknown";
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