package com.example.navegationt3.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Preview(showBackground = true)
@Composable
fun NavManager() {
    var navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "PrimerPantalla") {
        composable(route = "PrimerPantalla") {
            HomeView(navController)
        }
        composable(
            route = "obtenerMensaje/{nombre}/{id}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { parameters ->
            val nombre = parameters.arguments?.getString("nombre") ?: ""
            val id = parameters.arguments?.getInt("id") ?: 0

            MessageView(navController, nombre, id)
        }
    }
}
