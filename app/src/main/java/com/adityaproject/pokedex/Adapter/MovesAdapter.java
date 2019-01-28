package com.adityaproject.pokedex.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adityaproject.pokedex.R;

import butterknife.ButterKnife;

public class MovesAdapter extends RecyclerView.Adapter<MovesAdapter.PokemonMovesViewHolder> {


    Context mContext;


    @Override
    public PokemonMovesViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemonmoves, parent, false);
        return new PokemonMovesViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(PokemonMovesViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PokemonMovesViewHolder extends RecyclerView.ViewHolder {


        public PokemonMovesViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }


    }
}
