package com.example.lab13_animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Definición del enum class fuera de la función composable
enum class ScreenState {
    Loading,
    Content,
    Error
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    var currentState by remember { mutableStateOf(ScreenState.Loading) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // AnimatedContent que cambia entre diferentes estados
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 300)) with fadeOut(animationSpec = tween(durationMillis = 300))
            }
        ) { state ->
            when (state) {
                ScreenState.Loading -> {
                    Text("Cargando...", modifier = Modifier.padding(16.dp))
                }
                ScreenState.Content -> {
                    Text("Contenido cargado exitosamente!", modifier = Modifier.padding(16.dp))
                }
                ScreenState.Error -> {
                    Text("Error al cargar el contenido.", modifier = Modifier.padding(16.dp))
                }
            }
        }

        // Botón para cambiar de estado
        Button(
            onClick = {
                // Cambia el estado cíclicamente
                currentState = when (currentState) {
                    ScreenState.Loading -> ScreenState.Content
                    ScreenState.Content -> ScreenState.Error
                    ScreenState.Error -> ScreenState.Loading
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
        ) {
            Text("Cambiar Estado")
        }
    }
}