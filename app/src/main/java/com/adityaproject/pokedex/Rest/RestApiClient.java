package com.adityaproject.pokedex.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    // public static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String BASE_URL) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

}
