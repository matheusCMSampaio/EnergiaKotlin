package br.com.fiap.energia_kotlin.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import br.com.fiap.energia_kotlin.model.Dispositivo

@Composable
fun ListarDispositivosScreen(
    dispositivos: List<Dispositivo>,
    onEdit: (Dispositivo) -> Unit,
    onDelete: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(dispositivos, key = { it.id ?: -1L }) { dispositivo ->
            DispositivoItem(
                dispositivo = dispositivo,
                onEdit = onEdit,
                onDelete = onDelete
            )
        }
    }
}

@Composable
fun DispositivoItem(
    dispositivo: Dispositivo,
    onEdit: (Dispositivo) -> Unit,
    onDelete: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(dispositivo.nome)
        Row {
            Button(
                onClick = { onEdit(dispositivo) },
                modifier = Modifier.semantics { contentDescription = "Editar ${dispositivo.nome}" }
            ) {
                Text("Editar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { dispositivo.id?.let { onDelete(it) } },
                modifier = Modifier.semantics { contentDescription = "Excluir ${dispositivo.nome}" }
            ) {
                Text("Excluir")
            }
        }
    }
}
