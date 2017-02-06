package com.reto_final_android.service;

import com.reto_final_android.entity.PokemonEntity;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;


public interface ApiService {

    @GET("/lista_pokemons.php")
    void getPokemons(Callback<ArrayList<PokemonEntity>> callback);
}
