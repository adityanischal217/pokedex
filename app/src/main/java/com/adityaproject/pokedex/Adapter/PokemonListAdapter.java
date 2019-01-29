package com.adityaproject.pokedex.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adityaproject.pokedex.Interface.Listener;
import com.adityaproject.pokedex.Model.PokemonResult;
import com.adityaproject.pokedex.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder> {

    private List<PokemonResult> mPokemonlist;
    private Context mContext;
    Listener mLISTENER;

    public PokemonListAdapter(Context context, Listener mListener, List<PokemonResult> pokemonlist) {
        mContext = context;
        mPokemonlist = pokemonlist;
        mLISTENER = mListener;
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
        String upperName = NAME.substring(0, 1).toUpperCase() + NAME.substring(1);
        holder.mPokemomn.setText(upperName);
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
            String url = mPokemonlist.get(position).getUrl();
            mLISTENER.responseListener(url);

        }
    }
}
