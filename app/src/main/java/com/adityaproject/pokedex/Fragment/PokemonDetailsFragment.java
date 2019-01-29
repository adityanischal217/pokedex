package com.adityaproject.pokedex.Fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adityaproject.pokedex.Adapter.StatsAdapter;
import com.adityaproject.pokedex.Adapter.TypesAdapter;
import com.adityaproject.pokedex.AppConstantKeys;
import com.adityaproject.pokedex.Model.GameIndex;
import com.adityaproject.pokedex.Model.Move;
import com.adityaproject.pokedex.Model.PokemonDetails;
import com.adityaproject.pokedex.Model.Stat;
import com.adityaproject.pokedex.Model.Type;
import com.adityaproject.pokedex.R;
import com.adityaproject.pokedex.Rest.RestApiClient;
import com.adityaproject.pokedex.Rest.RestApiInterface;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
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
    @BindView(R.id.types_rv)
    RecyclerView mTypesRV;
    @BindView(R.id.pokemondetails_relativelu)
    RelativeLayout mLayout;
    StatsAdapter mStatsAdapter;
    TypesAdapter mTypesAdapter;
    @BindView(R.id.stats_tv)
    TextView ml;
    String name, sprites, height, weight, base_experience, id, abilities;
    List<Move> moves;
    List<Stat> stats;
    List<Type> types;
    Fragment fragment;
    List<GameIndex> game_indices;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    ProgressDialog progress;
    @BindView(R.id.nointernet_tv1)
    TextView mNoInternetTV;

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
        return view;
    }

    private void init() {
        context = getContext();
        progress = new ProgressDialog(context);
        URL = getArguments().getString(AppConstantKeys.POKEDEX_URL_STRING);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        callAPI();
    }

    private void callAPI() {
        progress.setMessage("Loading...");
        progress.setCancelable(true);
        progress.setCanceledOnTouchOutside(true);
        progress.show();
        RestApiInterface apiService =
                RestApiClient.getClient(URL).create(RestApiInterface.class);
        Call<PokemonDetails> call = apiService.getPokemonDetails();
        call.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                mLayout.setVisibility(View.VISIBLE);
                name = response.body().getName();
                id = String.valueOf(response.body().getId());
                sprites = response.body().getSprites().getFrontDefault();
                height = String.valueOf(response.body().getHeight());
                base_experience = String.valueOf(response.body().getBaseExperience());
                weight = String.valueOf(response.body().getWeight());
                moves = response.body().getMoves();
                abilities = response.body().getAbilities().get(0).getAbility().getName();
                stats = response.body().getStats();
                game_indices = response.body().getGameIndices();
                types = response.body().getTypes();
                setViews();
                setAdapterViews();
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {

                progress.dismiss();
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setCancelable(false);
                dialog.setMessage("Please Check Your Internet Connection");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mNoInternetTV.setVisibility(View.VISIBLE);
                    }
                });
                dialog.show();
                // Log error here since request failed
            }
        });

    }

    private void setAdapterViews() {
        setStatsAdapter(stats);
        setTypesAdapter(types);
    }

    @SuppressLint("SetTextI18n")
    private void setViews() {
        Glide.with(context).load(sprites)
                .thumbnail(0.5f)
                .into(mImage);
        String upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
        mId.setText("#" + "" + id);
        mName.setText(upperName);
        mHeight.setText(height);
        mAbilities.setText(abilities);
        mWeight.setText(weight + " " + "lbs");
        mBaseExp.setText(base_experience);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.moves_tab:
                    fragment = new PokemonMovesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(AppConstantKeys.MOVES_ARRAYLIST_, (ArrayList<? extends Parcelable>) moves);
                    fragment.setArguments(bundle);
                    openFragment(fragment);
                    return true;
                case R.id.gameindices_tab:
                    fragment = new GamegIndicesFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putParcelableArrayList(AppConstantKeys.GAMEINDICES_ARRAYLIST_, (ArrayList<? extends Parcelable>) game_indices);
                    fragment.setArguments(bundle1);
                    openFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void setStatsAdapter(List<Stat> stats) {
        mStatsAdapter = new StatsAdapter(context, stats);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mStatsRV.setLayoutManager(layoutManager);
        mStatsRV.setItemAnimator(new DefaultItemAnimator());
        mStatsRV.setAdapter(mStatsAdapter);
    }

    private void setTypesAdapter(List<Type> types) {
        mTypesAdapter = new TypesAdapter(context, types);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mTypesRV.setLayoutManager(layoutManager);
        mTypesRV.setItemAnimator(new DefaultItemAnimator());
        mTypesRV.setAdapter(mTypesAdapter);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack("mmm");
        transaction.commit();
    }


}
