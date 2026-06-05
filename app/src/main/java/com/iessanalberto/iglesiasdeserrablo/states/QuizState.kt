package com.iessanalberto.iglesiasdeserrablo.states

import com.iessanalberto.iglesiasdeserrablo.models.Iglesia

data class QuizState(
    val iglesiaCorrecta: Iglesia? = null,
    val opciones: List<Iglesia> = emptyList(),
    val zoomLevel: Float = 5f,
    val opcionesDeshabilitadas: Set<String> = emptySet(),
    val juegoTerminado: Boolean = false,
    val mensaje: String = ""
)
