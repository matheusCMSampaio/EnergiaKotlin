package br.com.fiap.energia_kotlin.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.energia_kotlin.model.Dispositivo

//@Composable
//fun EditarDispositivoScreen(dispositivo: Dispositivo, onSave: (Dispositivo) -> Unit) {
//    val nome by remember { mutableStateOf(dispositivo.nome) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
//        Spacer(modifier = Modifier.height(16.dp))
//        OutlinedTextField(value = descricao, onValueChange = { descricao = it }, label = { Text("Descrição") })
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = { onSave(Dispositivo(dispositivo.id, nome, descricao)) }) {
//            Text("Salvar Alterações")
//        }
//    }
//}
//
//private operator fun Any.getValue(nothing: Nothing?, property: KProperty<*>): Any {
//
//}
