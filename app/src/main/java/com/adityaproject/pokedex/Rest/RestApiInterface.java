package com.adityaproject.pokedex.Rest;

import com.adityaproject.pokedex.Model.PokemonData;
import com.adityaproject.pokedex.Model.PokemonDetails;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiInterface {


    @GET("./")
    Call<PokemonData> getPokemonList();

    @GET("./")
    Call<PokemonDetails> getPokemonDetails();


}
