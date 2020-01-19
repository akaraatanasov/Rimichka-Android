package com.nbu.rimichka.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nbu.rimichka.R;
import com.nbu.rimichka.models.Rhyme;
import com.nbu.rimichka.models.RhymePair;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesAdapter favoritesAdapter;
    private FavoritesViewModel favoritesViewModel;

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        favoritesViewModel.getAllRhymePairs().observe(this, new Observer<List<RhymePair>>() {
            @Override
            public void onChanged(List<RhymePair> rhymePairs) {
                favoritesAdapter.setPairs(rhymePairs);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoritesAdapter = new FavoritesAdapter(getContext());
        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoritesAdapter);
    }
}