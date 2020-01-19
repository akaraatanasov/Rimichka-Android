package com.nbu.rimichka.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchAdapter searchAdapter;
    private SearchViewModel searchViewModel;

    private EditText searchEditText;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchAdapter = new SearchAdapter(getContext(), searchViewModel);
        recyclerView = view.findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchAdapter);

        searchEditText = view.findViewById(R.id.search_edit_text);
        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onTapSearchButton();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchViewModel.getRhymeList().observe(this, new Observer<ArrayList<Rhyme>>() {
            @Override
            public void onChanged(ArrayList<Rhyme> rhymeResponses) {
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    private void onTapSearchButton() {
        String query = searchEditText.getText().toString();
        searchViewModel.executeSearch(query);

        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}