package br.com.fiap.energia_kotlin.`interface`

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://webapp-gs-rm551856-gua5d8fvfmcmcycs.brazilsouth-01.azurewebsites.net" // Substitua pelo URL base da API

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
