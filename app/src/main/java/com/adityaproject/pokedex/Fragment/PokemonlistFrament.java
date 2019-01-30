package com.adityaproject.pokedex.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.adityaproject.pokedex.Adapter.PokemonListAdapter;
import com.adityaproject.pokedex.AppConstantKeys;
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

    @BindView(R.id.pokemonlist_rv)
    RecyclerView mPokemonRV;
    @BindView(R.id.nointernet_tv)
    TextView mNoInternetTV;
    PokemonListAdapter pokemonDataAdapter;
    Context context;
    ProgressDialog progress;

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
        return view;
    }

    private void init() {
        context = getContext();
        progress = new ProgressDialog(context);
        callAPI();
    }

    private void callAPI() {
        progress.setMessage("Loading...");
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        RestApiInterface apiService =
                RestApiClient.getClient(AppConstantKeys.BASE_URL).create(RestApiInterface.class);
        Call<PokemonData> call = apiService.getPokemonList();
        call.enqueue(new Callback<PokemonData>() {
            @Override
            public void onResponse(Call<PokemonData> call, Response<PokemonData> response) {
                List<PokemonResult> pokemonlist = response.body().getResults();
                setRvAdapter(pokemonlist);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<PokemonData> call, Throwable t) {
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
            }
        });

    }

    private void setRvAdapter(List<PokemonResult> pokemonlist) {
        pokemonDataAdapter = new PokemonListAdapter(context, this, pokemonlist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mPokemonRV.setLayoutManager(layoutManager);
        mPokemonRV.setItemAnimator(new DefaultItemAnimator());
        mPokemonRV.setAdapter(pokemonDataAdapter);
    }

    @Override
    public void responseListener(String URL) {
        Fragment fragment = new PokemonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstantKeys.POKEDEX_URL_STRING, URL);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack("asd");
        fragmentTransaction.commit();

    }
}
