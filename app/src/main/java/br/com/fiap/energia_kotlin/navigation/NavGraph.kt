package br.com.fiap.energia_kotlin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.energia_kotlin.activity.DispositivoViewModel
import br.com.fiap.energia_kotlin.activity.ExploreScreen
import br.com.fiap.energia_kotlin.activity.InicioScreen
import br.com.fiap.energia_kotlin.activity.ListarDispositivosScreen
import br.com.fiap.energia_kotlin.activity.LoginScreen
import br.com.fiap.energia_kotlin.activity.Registro
import br.com.fiap.energia_kotlin.activity.TodoListScreen
import br.com.fiap.energia_kotlin.model.Dispositivo

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("registrar")
    object TodoList : Screen("todolist")
    object Inicio : Screen("inicio")
    object Listagem : Screen("listagem")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            ExploreScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(onBackClick = { navController.popBackStack() }, navController= navController)
        }
        composable(route = Screen.Register.route) {
            Registro(navController = navController)
        }
        composable(Screen.TodoList.route) {
            TodoListScreen(navController = navController)
        }
        composable(Screen.Inicio.route) {
            InicioScreen(navController = navController)
        }


        composable(Screen.Listagem.route) {
            val viewModel: DispositivoViewModel = viewModel()
            val dispositivos by viewModel.dispositivos.collectAsState()

            ListarDispositivosScreen(
                dispositivos = dispositivos,
                onEdit = { dispositivo ->
                    // Navegar para a tela de edição
                    navController.navigate("edit/${dispositivo.id}")
                },
                onDelete = { id ->
                    // Chamar a função de exclusão na ViewModel
                    viewModel.deleteDispositivo(id)
                }
            )
        }
//
//        // Tela para edição de dados
//        composable(Screen.Editar.route) { backStackEntry ->
//            // Passa parâmetros (ex.: ID do item a ser editado) via argumentos da navegação
//            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
//            EditarDadosScreen(navController = navController, itemId = itemId)
//        }
//
//        // Tela para exclusão de dados
//        composable(Screen.Excluir.route) { backStackEntry ->
//            // Passa parâmetros (ex.: ID do item a ser excluído) via argumentos da navegação
//            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
//            ExcluirDadosScreen(navController = navController, itemId = itemId)
//        }
    }
}