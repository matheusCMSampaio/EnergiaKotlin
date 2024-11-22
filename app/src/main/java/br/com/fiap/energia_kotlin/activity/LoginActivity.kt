package br.com.fiap.energia_kotlin.activity

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.energia_kotlin.`interface`.RetrofitClient
import br.com.fiap.energia_kotlin.`interface`.UsuarioApi
import br.com.fiap.energia_kotlin.model.Usuario
import br.com.fiap.energia_kotlin.navigation.Screen
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import retrofit2.create


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(onBackClick: () -> Unit = {}, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Login.", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) { // Navega para a tela Home
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Image(
                        painter = rememberAsyncImagePainter("https://s3-alpha-sig.figma.com/img/fb12/fd24/7fb35c8b49f450982fee3df7abe8edce?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=P8eThHFQ3ZTZVW9hUdAA2GdKG6ZSCrUWGf~vWEbDEJBWfAMUsTj2I1WN73CwZg1~Z-ZDtycP5vnEDtz0D22-QYPsPdRicqI16StlB4vcxh9kTprVlTNzGmv2M8svUO54YljXXJ2~pjESrHEa1Drs3oiuIj4reQ1sSIgUNQGmAweuFouVVmDDZEYS~NvfmSHq4XC63vJfwXP24EJIOu-mTArAJRIPX-4wUjz4pdWsJp1b6-qNI-3M035o6-nGOyi0jsF8Lvns-IttRsV5YJhLyOgnd8~JammmObp2qJDICPM-AZUbzq~LPa7V~U6TMCyFvMzykrBH-yObzNkENJ~Xkg__"),
                        contentDescription = "Logo",
                        modifier = Modifier.size(50.dp).padding(end = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF3B2678))
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF3B2678))
                    .padding(horizontal = 16.dp, vertical = paddingValues.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Imagem principal
                Image(
                    painter = rememberAsyncImagePainter("https://s3-alpha-sig.figma.com/img/a3f5/227b/b504457594aef1a0f1c68cd797cd2430?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Q2VCL2XM0TUHaCS2kX4y5FHHdLOGoVIB8A1CjXq8QXtXU6yhg-~IUpNBExV0mO2cwVO974vuHP4XSVBo011hZ~lbwyrmc0koIMmgjPb886PiXvnTYfkq1FdYq41kXxT6m4m2fG8VMkmNv-r8SBABP8oDfVpStaVQQCbIAde07vCDqbuFyJlFOKgPPKt6YeXPxAgc4bB4B40nOr3zVTE4cF8RIBjDMQq3QAQMgjvxzpDRluGYSzrKoyBLg0ioGu9AROYJPN8Qskzl~JIFbpN8nJK9CuQNgkGXlSrhw7ZSfTPpkRt7-TUu2WSut3tXpplXvsey-KQwScAZnVRELzmmLA__"),
                    contentDescription = "Login Illustration",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de entrada
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("E-mail", color = Color.Gray) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = Color.Gray)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF8FF557),
                        unfocusedBorderColor = Color.Gray,

                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Senha", color = Color.Gray) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Color.Gray)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF8FF557),
                        unfocusedBorderColor = Color.Gray,

                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botão de login
                Button(
                    onClick = {
                        navController.navigate(Screen.Inicio.route)
                        val usuario = Usuario(email = email, senha = password)

                        scope.launch {
                            try {
                                val response = RetrofitClient.instance.create(UsuarioApi::class.java).findByEmail(usuario)
                                if (response.isSuccessful) {

                                }
                            } catch (e: Exception) {
                                errorMessage = "Falha na conexão: ${e.localizedMessage}"
                                Log.e("Login", "Erro: ${e.message}")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8FF557)),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("ENTRAR", color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Link de "Esqueceu sua senha?"
                Text(
                    text = "Esqueceu sua senha?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Lógica para recuperação de senha */ }
                )
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun view(){
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
