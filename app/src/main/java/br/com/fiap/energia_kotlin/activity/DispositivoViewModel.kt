package br.com.fiap.energia_kotlin.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.energia_kotlin.`interface`.DispositivoService
import br.com.fiap.energia_kotlin.model.Dispositivo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DispositivoViewModel : ViewModel() {
    private val _dispositivos = MutableStateFlow<List<Dispositivo>>(emptyList())
    val dispositivos: StateFlow<List<Dispositivo>> = _dispositivos

    private val dispositivoService: DispositivoService

    init {
        // Configura o Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://webapp-gs-rm551856-gua5d8fvfmcmcycs.brazilsouth-01.azurewebsites.net") // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dispositivoService = retrofit.create(DispositivoService::class.java)

        // Carrega os dispositivos ao iniciar
        fetchDispositivos()
    }

    private fun fetchDispositivos() {
        viewModelScope.launch {
            try {
                val response = dispositivoService.findAll().execute()
                if (response.isSuccessful) {
                    _dispositivos.value = response.body() ?: emptyList()
                } else {
                    // Lidar com erros de API
                    println("Erro ao buscar dispositivos: ${response.code()}")
                }
            } catch (e: Exception) {
                // Lidar com erros de rede
                println("Erro ao buscar dispositivos: ${e.message}")
            }
        }
    }

    fun deleteDispositivo(id: Long) {
        viewModelScope.launch {
            try {
                val response = dispositivoService.delete(id).execute()
                if (response.isSuccessful) {
                    _dispositivos.value = _dispositivos.value.filter { it.id != id }
                } else {
                    println("Erro ao deletar dispositivo: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Erro ao deletar dispositivo: ${e.message}")
            }
        }
    }
}
