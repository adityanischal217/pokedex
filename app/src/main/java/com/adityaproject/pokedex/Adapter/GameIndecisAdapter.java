package com.adityaproject.pokedex.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adityaproject.pokedex.Model.GameIndex;
import com.adityaproject.pokedex.Model.Stat;
import com.adityaproject.pokedex.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameIndecisAdapter extends RecyclerView.Adapter<GameIndecisAdapter.PokemonGIHolder> {

    Context mContext;
    ArrayList<GameIndex> mIndices;

    public GameIndecisAdapter(Context context, ArrayList<GameIndex> indices) {
        mContext = context;
        mIndices = indices;
    }

    @Override
    public PokemonGIHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemongameindices, parent, false);
        return new PokemonGIHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PokemonGIHolder holder, final int position) {
        String index = String.valueOf(mIndices.get(position).getGameIndex());
        String name = mIndices.get(position).getVersion().getName();
        String upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
        holder.mGameIndex.setText(":" + " " + index);
        holder.mVersionName.setText(":" + " " + upperName);
    }

    @Override
    public int getItemCount() {
        return mIndices.size();
    }

    public class PokemonGIHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gameindices_tv)
        TextView mGameIndex;
        @BindView(R.id.version_tv)
        TextView mVersionName;

        public PokemonGIHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}
