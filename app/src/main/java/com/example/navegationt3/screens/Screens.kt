package com.example.navegationt3.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun HomeView(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nombre:")
        TextField(value = nombre, onValueChange = { nombre = it })
        Text(text = "ID:")
        TextField(value = id, onValueChange = { id = it })

        Button(
            onClick = {
                navController.navigate("obtenerMensaje/$nombre/${id.toInt()}")
            }
        ) {
            Text(text = "Siguiente")
        }
    }
}


fun obtenerMensaje(nombre: String, id: Int): String {
    val idIsnd = listOf(19475, 19508, 19523, 19666, 21637, 21767, 22098, 22154, 22180, 22208, 22210)
    return when {
        id <= 10 -> "Bienvenido al laboratorio de ISND, estimado coordinador $nombre."
        id <= 100 -> "Permiso autorizado para el profesor $nombre."
        id <= 15000 -> "Acceso denegado a egresados."
        id in idIsnd -> "Alumno $nombre autorizado para uso del laboratorio."
        else -> "Este laboratorio es de uso exclusivo para la carrera ISND."
    }
}

@Composable
fun MessageView(navController: NavHostController, nombre: String, id: Int) {
    val message = obtenerMensaje(nombre, id)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 25.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults
                .buttonColors(Color(0xFFFF7700))
        ) {
            Text(text = "Regresar")
        }
    }
}
