package com.example.traveljournal.trip_recycler_view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    //private final ImageView picture;
    private final TextView textViewTripName, textViewDestination, textViewPrice;
    private final ConstraintLayout constraintLayoutTrip;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        //picture = itemView.findViewById(R.id.imageTrip);
        textViewTripName = itemView.findViewById(R.id.textViewTripName);
        textViewDestination = itemView.findViewById(R.id.textViewDestination);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        constraintLayoutTrip = itemView.findViewById(R.id.constraintLayoutTripItem);
    }

    /*public ImageView getPicture() {
        return picture;
    }*/

    public TextView getTextViewTripName() {
        return textViewTripName;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public TextView getTextViewPrice() {
        return textViewPrice;
    }

    public ConstraintLayout getConstraintLayoutTrip() {
        return constraintLayoutTrip;
    }
}
