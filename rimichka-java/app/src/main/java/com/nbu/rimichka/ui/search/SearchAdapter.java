package com.nbu.rimichka.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nbu.rimichka.R;
import com.nbu.rimichka.models.RhymePair;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    class SearchViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        SearchViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    private final LayoutInflater inflater;
    private SearchViewModel viewModel;

    SearchAdapter(Context context, SearchViewModel viewModel) {
        this.inflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View createdView = inflater.inflate(R.layout.rhyme_view_item, parent, false);
        return new SearchViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        if (viewModel.getRhymeList().getValue() != null) {
            TextView rhymeText = holder.itemView.findViewById(R.id.rhyme_text);
            rhymeText.setText(viewModel.getRhymeList().getValue().get(position).wrd);

            ImageButton rhymeButton = holder.itemView.findViewById(R.id.rhyme_button);
            rhymeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rhymeButton.setImageResource(R.drawable.ic_favorite);
                    String rhyme = rhymeText.getText().toString();
                    viewModel.insert(rhyme);
                }
            });
        } else {
            TextView rhymeText = holder.itemView.findViewById(R.id.rhyme_text);
            rhymeText.setText("Няма рими или интернет!");
        }
    }

    @Override
    public int getItemCount() {
        if (viewModel.getRhymeList().getValue() != null)
            return viewModel.getRhymeList().getValue().size();
        else return 1;
    }
}
