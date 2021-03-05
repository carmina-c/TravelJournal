package com.example.traveljournal.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.room.Trip;
import com.example.traveljournal.room.TripViewModel;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private TripViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewFavoriteTrips);

        final FavoriteTripListAdapter adapter = new FavoriteTripListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoritesViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        favoritesViewModel.getFavoriteTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable final List<Trip> trips) {
                adapter.setTrips(trips);
            }
        });

        return root;
    }
}
