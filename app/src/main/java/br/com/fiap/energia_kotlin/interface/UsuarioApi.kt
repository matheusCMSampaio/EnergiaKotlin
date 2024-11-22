package br.com.fiap.energia_kotlin.`interface`

import br.com.fiap.energia_kotlin.model.Usuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

// Definição da interface com funções suspensas (suspend)
interface UsuarioApi {
    @POST("/api/user/usuario")
    suspend fun findByEmail(@Body usuario: Usuario): Response<Usuario>
}


