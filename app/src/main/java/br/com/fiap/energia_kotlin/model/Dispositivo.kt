package br.com.fiap.energia_kotlin.model


data class Dispositivo (
    val id: Long? = null,
    val nome: String,
    val tipo: String,
    val localizacao: String,
    val status: String,
    val dataCadastro: String? = null
)