package com.adityaproject.pokedex.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adityaproject.pokedex.Adapter.MovesAdapter;
import com.adityaproject.pokedex.Adapter.TypesAdapter;
import com.adityaproject.pokedex.AppConstantKeys;
import com.adityaproject.pokedex.Model.Move;
import com.adityaproject.pokedex.Model.Type;
import com.adityaproject.pokedex.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonMovesFragment extends Fragment {

    ArrayList<Move> moves;
    Context context;
    MovesAdapter movesAdapter;
    @BindView(R.id.moves_rv)
    RecyclerView mMovesRV;

    public PokemonMovesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_moves, container, false);
        ButterKnife.bind(this, view);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Moves");
        init();
        return view;
    }

    private void init() {
        context = getContext();
        moves = getArguments().getParcelableArrayList(AppConstantKeys.MOVES_ARRAYLIST_);
        setAdapter(moves);
    }

    private void setAdapter(ArrayList<Move> moves) {
        movesAdapter = new MovesAdapter(context, moves);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mMovesRV.setLayoutManager(layoutManager);
        mMovesRV.setItemAnimator(new DefaultItemAnimator());
        mMovesRV.setAdapter(movesAdapter);
    }
}
