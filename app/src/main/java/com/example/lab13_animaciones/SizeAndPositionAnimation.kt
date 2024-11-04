package com.example.lab13_animaciones
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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

@Composable
fun SizeAndPositionAnimationExample() {
    var isLarge by remember { mutableStateOf(false) }
    var isMoved by remember { mutableStateOf(false) }

    // Animación del tamaño
    val size by animateDpAsState(
        targetValue = if (isLarge) 200.dp else 100.dp, // Cambia entre 100 y 200 dp
        animationSpec = tween(durationMillis = 500) // Duración de la animación
    )

    // Calcula el desplazamiento
    val offset by animateDpAsState(
        targetValue = if (isMoved) 200.dp else 0.dp, // Desplazamiento en el eje X
        animationSpec = tween(durationMillis = 500) // Duración de la animación
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Botón que activa el cambio de tamaño y posición
        Button(
            onClick = {
                isLarge = !isLarge
                isMoved = !isMoved
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Mover y Cambiar Tamaño")
        }

        // Cuadro que se anima en tamaño y posición
        Box(
            modifier = Modifier
                .size(size) // Tamaño animado
                .offset(x = offset, y = 0.dp) // Desplazamiento animado
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
        }
    }
}