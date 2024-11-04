package com.example.lab13_animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorChangeExample() {
    var isBlue by remember { mutableStateOf(true) }

    // Animación de color
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 1000) // Cambia el color en 1 segundo
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Botón llamativo que cambia el color de fondo
        Button(
            onClick = { isBlue = !isBlue },
            modifier = Modifier
                .size(200.dp) // Tamaño del botón
                .padding(16.dp), // Espaciado alrededor del botón
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White) // Color de fondo del botón
        ) {
            Text(
                text = "Cambiar Color",
                color = Color.Black // Color del texto
            )
        }

        // Cuadro con el color de fondo animado
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(backgroundColor)
                .padding(16.dp), // Espacio interno para el cuadro
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isBlue) "Azul" else "Verde", // Texto que indica el color actual
                color = Color.White // Color del texto en el cuadro
            )
        }
    }
}