package com.iessanalberto.iglesiasdeserrablo.components


import androidx.compose.remote.creation.compose.state.min
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.min

class RomanesqueWindowShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val arcHeight = min(width * 0.5f, height * 0.2f) // Altura del arco limitada

            // Comenzamos en la esquina inferior izquierda
            moveTo(0f, height)

            // Subimos por el lado izquierdo
            lineTo(0f, arcHeight)

            // Arco superior
            arcTo(
                rect = Rect(0f, 0f, width, arcHeight * 2),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            // Bajamos por el lado derecho
            lineTo(width, height)

            // Cerramos (línea horizontal en el fondo)
            lineTo(0f, height)

            close()
        }

        return Outline.Generic(path)
    }
}