package com.example.traveljournal.trip_recycler_view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.NewTripActivity;
import com.example.traveljournal.R;
import com.example.traveljournal.TripDetailsActivity;

public class TripViewHolder extends RecyclerView.ViewHolder {

    //private final ImageView picture;
    private final TextView textViewTripName, textViewDestination, textViewPrice;
    private final ConstraintLayout constraintLayoutTrip;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);
        Context context = itemView.getContext();

        //picture = itemView.findViewById(R.id.imageTrip);
        textViewTripName = itemView.findViewById(R.id.textViewTripName);
        textViewDestination = itemView.findViewById(R.id.textViewDestination);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        constraintLayoutTrip = itemView.findViewById(R.id.constraintLayoutTripItem);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View itemView) {
                Toast.makeText(itemView.getContext(), "Position is " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(context, NewTripActivity.class);
                context.startActivity(activity);
                return false;
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(context, TripDetailsActivity.class);
                context.startActivity(activity);
            }
        });
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
