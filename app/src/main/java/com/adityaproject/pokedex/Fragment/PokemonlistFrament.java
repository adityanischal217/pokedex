package com.adityaproject.pokedex.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adityaproject.pokedex.Adapter.PokemonDataAdapter;
import com.adityaproject.pokedex.Interface.Listener;
import com.adityaproject.pokedex.Model.PokemonData;
import com.adityaproject.pokedex.Model.PokemonResult;
import com.adityaproject.pokedex.R;
import com.adityaproject.pokedex.Rest.RestApiClient;
import com.adityaproject.pokedex.Rest.RestApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonlistFrament extends Fragment implements Listener {

    private static final String TAG = "Debugtag";
    @BindView(R.id.pokemonlist_rv)
    RecyclerView mPokemonRV;
    PokemonDataAdapter pokemonDataAdapter;
    Context context;
    String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonlistFrament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pokemonlist_frament, container, false);
        ButterKnife.bind(this, view);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Pokedox");
        init();
        // Inflate the layout for this fragment
        return view;
    }

    private void init() {
        context = getContext();
        callAPI();
    }

    private void callAPI() {

        RestApiInterface apiService =
                RestApiClient.getClient(BASE_URL).create(RestApiInterface.class);
        Call<PokemonData> call = apiService.getPokemonList();
        call.enqueue(new Callback<PokemonData>() {
            @Override
            public void onResponse(Call<PokemonData> call, Response<PokemonData> response) {
                List<PokemonResult> pokemonlist = response.body().getResults();
                setRvAdapter(pokemonlist);
            }

            @Override
            public void onFailure(Call<PokemonData> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

    private void setRvAdapter(List<PokemonResult> pokemonlist) {
        pokemonDataAdapter = new PokemonDataAdapter(context, this, pokemonlist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mPokemonRV.setLayoutManager(layoutManager);
        mPokemonRV.setItemAnimator(new DefaultItemAnimator());
        mPokemonRV.setAdapter(pokemonDataAdapter);

    }


    @Override
    public void responseListener(String URL) {
        Fragment fragment = new PokemonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("urla", URL);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }
}
