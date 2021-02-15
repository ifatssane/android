package com.example.testapprentissagetabbedactivity.API;

import com.example.testapprentissagetabbedactivity.Pokemon.PokemonAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceAPI {
    @GET("api/v2/pokemon/{id}")
    Call<PokemonAPI> GetPokemon(@Path("id") int id

    );
}
