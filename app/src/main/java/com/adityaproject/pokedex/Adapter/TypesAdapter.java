package com.adityaproject.pokedex.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adityaproject.pokedex.Model.Stat;
import com.adityaproject.pokedex.Model.Type;
import com.adityaproject.pokedex.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.PokemonTypesViewHolder> {


    Context mContext;
    List<Type> mTypes;

    public TypesAdapter(Context context, List<Type> types) {
        mContext = context;
        mTypes = types;
    }

    @Override
    public PokemonTypesViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemontypes, parent, false);
        return new PokemonTypesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PokemonTypesViewHolder holder, final int position) {
        String NAME = mTypes.get(position).getType().getName();
        String upperName = NAME.substring(0, 1).toUpperCase() + NAME.substring(1);
        holder.mTypes.setText(upperName);
    }
    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    public class PokemonTypesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.types_tv_adapter)
        TextView mTypes;

        public PokemonTypesViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
