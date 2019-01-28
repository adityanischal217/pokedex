package com.adityaproject.pokedex.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adityaproject.pokedex.Adapter.PokemonDataAdapter;
import com.adityaproject.pokedex.Adapter.StatsAdapter;
import com.adityaproject.pokedex.Model.Move;
import com.adityaproject.pokedex.Model.PokemonDetails;
import com.adityaproject.pokedex.Model.Stat;
import com.adityaproject.pokedex.R;
import com.adityaproject.pokedex.Rest.RestApiClient;
import com.adityaproject.pokedex.Rest.RestApiInterface;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokemonDetailsFragment extends Fragment {
    String URL;
    Context context;
    @BindView(R.id.pokemonimage_iv)
    ImageView mImage;
    @BindView(R.id.id_tv)
    TextView mId;
    @BindView(R.id.pokemonname_tv)
    TextView mName;

    @BindView(R.id.heightvalue_tv)
    TextView mHeight;
    @BindView(R.id.weightvalue_tv)
    TextView mWeight;
    @BindView(R.id.baseexpvalue_tv)
    TextView mBaseExp;
    @BindView(R.id.abilityvalue_tv)
    TextView mAbilities;

    @BindView(R.id.stats_rv)
    RecyclerView mStatsRV;

    StatsAdapter StatsAdapter;
    @BindView(R.id.stats_tv)
    TextView ml;
    String name, sprites, height, weight, base_experience, id, abilities;
    List<Move> moves;
    List<Stat> stats;

    public PokemonDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Pokedox Details");
        init();
        // Inflate the layout for this fragment
        return view;
    }

    private void init() {
        context = getContext();
        URL = getArguments().getString("urla");
        callAPI();
    }

    private void callAPI() {
        RestApiInterface apiService =
                RestApiClient.getClient(URL).create(RestApiInterface.class);
        Call<PokemonDetails> call = apiService.getPokemonDetails();
        call.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                name = response.body().getName();
                id = String.valueOf(response.body().getId());
                sprites = response.body().getSprites().getFrontDefault();
                height = String.valueOf(response.body().getHeight());
                base_experience = String.valueOf(response.body().getBaseExperience());
                weight = String.valueOf(response.body().getWeight());
                moves = response.body().getMoves();
                abilities = response.body().getAbilities().get(0).getAbility().getName();
                stats = response.body().getStats();


                Glide.with(context).load(sprites)
                        .thumbnail(0.5f)
                        .into(mImage);
                String upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
                mId.setText("#" + "" + id);
                mName.setText(upperName);
                mHeight.setText(height);
                mAbilities.setText(abilities);
                mWeight.setText(weight);
                mBaseExp.setText(base_experience);

                setAdapter(stats);


            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                // Log error here since request failed

            }
        });


    }

    private void setAdapter(List<Stat> stats) {

        StatsAdapter = new StatsAdapter(context, stats);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mStatsRV.setLayoutManager(layoutManager);
        mStatsRV.setItemAnimator(new DefaultItemAnimator());
        mStatsRV.setAdapter(StatsAdapter);

    }


}
