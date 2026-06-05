package com.iessanalberto.iglesiasdeserrablo.models

import androidx.compose.ui.graphics.painter.Painter
import kotlin.collections.listOf

data class Iglesia(
    val nombre: String = " ",
    val descripcionLong: String = " ",
    val descripcionShort: String = " ",
    val foto: String = " ",
    val ubicación: String = "",
    val listaFotos: List<String> = listOf(""),
    val descripcionTotal: String = " ",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)
