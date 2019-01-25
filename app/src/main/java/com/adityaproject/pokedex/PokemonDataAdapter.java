package com.adityaproject.pokedex;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PokemonDataAdapter extends RecyclerView.Adapter<PokemonDataAdapter.PokemonViewHolder> {

    private List<PokemonResult> mPokemonlist;
    private Context mContext;


    public PokemonDataAdapter(Context context, List<PokemonResult> pokemonlist) {
        mContext = context;
        mPokemonlist = pokemonlist;


    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemonlist, parent, false);
        return new PokemonViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(PokemonViewHolder holder, final int position) {
        String NAME = mPokemonlist.get(position).getName();
        String url = mPokemonlist.get(position).getUrl();
        holder.mPokemomn.setText(NAME);


    }

    @Override
    public int getItemCount() {
        return mPokemonlist.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pokemon_tv)
        TextView mPokemomn;


        public PokemonViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }


        @OnClick({R.id.relativelayout})
        public void onaaf() {
            int position = getAdapterPosition();

        }
    }
}
