package com.adityaproject.pokedex.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adityaproject.pokedex.Model.Move;
import com.adityaproject.pokedex.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovesAdapter extends RecyclerView.Adapter<MovesAdapter.PokemonMovesViewHolder> {
    ArrayList<Move> moves;

    Context mContext;

    public MovesAdapter(Context context, ArrayList<Move> moves) {
        mContext = context;
        this.moves = moves;
    }

    @Override
    public PokemonMovesViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemonmoves, parent, false);
        return new PokemonMovesViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PokemonMovesViewHolder holder, final int position) {
        String NAME = moves.get(position).getMove().getName();
        String level = moves.get(position).getVersionGroupDetails().get(0).getLevelLearnedAt().toString();
        String method = moves.get(position).getVersionGroupDetails().get(0).getMoveLearnMethod().getName();
        String upperName = NAME.substring(0, 1).toUpperCase() + NAME.substring(1);
        String upperMethod = method.substring(0, 1).toUpperCase() + method.substring(1);
        holder.mMovesName.setText(":" + " " +upperName);
        holder.mMovesMethod.setText(":" + " " +upperMethod);
        holder.mMovesLearn.setText(":" + " " +level);
    }

    @Override
    public int getItemCount() {
        return moves.size();
    }

    public class PokemonMovesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movesname_tv)
        TextView mMovesName;
        @BindView(R.id.movesmethod_tv)
        TextView mMovesMethod;
        @BindView(R.id.learn_tv)
        TextView mMovesLearn;

        public PokemonMovesViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }


    }
}
