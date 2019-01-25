package com.adityaproject.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiInterface {


    @GET("./")
    Call<PokemonData> getPokemonList();

    @GET("pokemon/1")
    Call<PokemonDetails> getPokemonDetails();


}
