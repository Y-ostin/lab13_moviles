package com.example.lab13_animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CombinedAnimationsScreen() {
    var isLarge by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }

    // Animación de tamaño del cuadro
    val size by animateDpAsState(
        targetValue = if (isLarge) 150.dp else 100.dp,
        animationSpec = tween(durationMillis = 300)
    )

    // Animación de color del cuadro
    val backgroundColor by animateColorAsState(
        targetValue = if (isLarge) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 300)
    )

    // Transición de contenido para el modo claro y oscuro
    val backgroundColorSurface = if (isDarkMode) Color.DarkGray else Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColorSurface),
        contentAlignment = Alignment.Center
    ) {
        // Cuadro que cambia de tamaño y color al hacer clic
        Box(
            modifier = Modifier
                .size(size)
                .background(backgroundColor)
                .clickable {
                    isLarge = !isLarge
                }
        )

        // Botón que se desplaza y desaparece
        AnimatedVisibility(visible = isButtonVisible) {
            Button(
                onClick = {
                    isButtonVisible = false
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000) // Espera 3 segundos
                        isButtonVisible = true // Regresa el botón a ser visible
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp) // Aumenta el padding para que no se superponga
            ) {
                Text("Desaparecer")
            }
        }

        // Botón para alternar entre modo claro y oscuro
        Button(
            onClick = {
                isDarkMode = !isDarkMode
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            Text("Modo ${if (isDarkMode) "Claro" else "Oscuro"}")
        }
    }
}