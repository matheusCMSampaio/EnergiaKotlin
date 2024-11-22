package br.com.fiap.energia_kotlin.`interface`

import retrofit2.Call
import br.com.fiap.energia_kotlin.model.Dispositivo
import retrofit2.http.*

interface DispositivoService {
    @POST("api/dispositivo")
    fun save(@Body dispositivo: Dispositivo): Call<String>

    @GET("api/dispositivo/all")
    fun findAll(): Call<List<Dispositivo>>

    @GET("api/dispositivo/{id}")
    fun findById(@Path("id") id: Long): Call<Dispositivo>

    @DELETE("api/dispositivo/{id}")
    fun delete(@Path("id") id: Long): Call<String>

    @PUT("api/dispositivo/{id}")
    fun update(@Path("id") id: Long, @Body dispositivo: Dispositivo): Call<Dispositivo>
}

