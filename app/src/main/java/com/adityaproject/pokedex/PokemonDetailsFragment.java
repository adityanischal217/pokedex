package com.adityaproject.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokemonDetailsFragment extends Fragment {
    public static String URL;

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
            actionBar.setTitle("Order Details");
        init();
        // Inflate the layout for this fragment
        return view;
    }

    private void init() {
       // callAPI();
       // URL =

    }

   /* private void callAPI() {
        RestApiInterface apiService =
                RestApiClient.getRetrofitClient().create(RestApiInterface.class);
        Call<PokemonDetails> call = apiService.getPokemonDetails();
        call.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {


            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                // Log error here since request failed

            }
        });


    }*/
}
