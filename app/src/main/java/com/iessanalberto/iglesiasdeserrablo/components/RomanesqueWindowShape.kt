package com.iessanalberto.iglesiasdeserrablo.components


import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class RomanesqueWindowShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val radius = width / 2f

            moveTo(0f, height)
            lineTo(0f, radius)

            arcTo(
                rect = Rect(0f, 0f, width, width),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            lineTo(width, height)
            close()
        }

        return Outline.Generic(path)
    }
}