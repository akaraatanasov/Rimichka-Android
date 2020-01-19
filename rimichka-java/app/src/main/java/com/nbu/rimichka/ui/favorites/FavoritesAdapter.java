package com.nbu.rimichka.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nbu.rimichka.R;
import com.nbu.rimichka.models.RhymePair;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    class FavoritesViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        FavoritesViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    private final LayoutInflater inflater;
    private FavoritesViewModel viewModel;


    FavoritesAdapter(Context context, FavoritesViewModel viewModel) {
        this.inflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View createdView = inflater.inflate(R.layout.rhyme_view_item, parent, false);
        return new FavoritesViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        if (viewModel.getAllRhymePairs().getValue() != null) {
            RhymePair pair = viewModel.getAllRhymePairs().getValue().get(position);

            TextView rhymeText = holder.itemView.findViewById(R.id.rhyme_text);
            rhymeText.setText(pair.getWord() + " -> " + pair.getRhyme());

            ImageButton rhymeButton = holder.itemView.findViewById(R.id.rhyme_button);
            rhymeButton.setImageResource(R.drawable.ic_favorite);
            rhymeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rhymeButton.setImageResource(R.drawable.ic_favorite_border);

                    viewModel.delete(pair);
                    notifyDataSetChanged();

                    System.out.println(pair + " removed from favorites!");
                }
            });
        } else {
            TextView rhymeText = holder.itemView.findViewById(R.id.rhyme_text);
            rhymeText.setText("Няма любими рими!");
        }
    }

    @Override
    public int getItemCount() {
        if (viewModel.getAllRhymePairs().getValue() != null)
            return viewModel.getAllRhymePairs().getValue().size();
        else return 0;
    }

}
