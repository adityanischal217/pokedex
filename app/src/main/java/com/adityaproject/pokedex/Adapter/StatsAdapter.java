package com.adityaproject.pokedex.Adapter;


import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adityaproject.pokedex.Model.Stat;
import com.adityaproject.pokedex.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.PokemonStatsViewHolder> {


    Context mContext;
    List<Stat> mStats;
    private int progressCount;
    private Handler progressHandler = new Handler();

    public StatsAdapter(Context context, List<Stat> stats) {
        mContext = context;
        mStats = stats;
    }

    @Override
    public PokemonStatsViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemonstats, parent, false);
        return new PokemonStatsViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final PokemonStatsViewHolder holder, final int position) {
        String NAME = mStats.get(position).getStat().getName();
        String Value = String.valueOf(mStats.get(position).getBaseStat());
        String upperName = NAME.substring(0, 1).toUpperCase() + NAME.substring(1);
        holder.mstatsName.setText(upperName);
        holder.mstatsValue.setText(Value);
        holder.mProgress.setProgress(Integer.parseInt(Value));
    }

    @Override
    public int getItemCount() {
        return mStats.size();
    }

    public class PokemonStatsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.statsname_tv)
        TextView mstatsName;
        @BindView(R.id.statsvalue_tv)
        TextView mstatsValue;
        @BindView(R.id.progressbar)
        ProgressBar mProgress;


        public PokemonStatsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);



        }


    }
}
