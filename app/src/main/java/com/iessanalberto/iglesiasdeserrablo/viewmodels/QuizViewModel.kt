package com.iessanalberto.iglesiasdeserrablo.viewmodels

import androidx.lifecycle.ViewModel
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.states.QuizState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state.asStateFlow()

    init {
        reiniciarJuego()
    }

    fun reiniciarJuego() {
        val correcta = listaIglesias.random()
        val otrasOpciones = listaIglesias.filter { it.nombre != correcta.nombre }.shuffled().take(3)
        val todasLasOpciones = (otrasOpciones + correcta).shuffled()

        _state.value = QuizState(
            iglesiaCorrecta = correcta,
            opciones = todasLasOpciones,
            zoomLevel = 5f,
            opcionesDeshabilitadas = emptySet(),
            juegoTerminado = false,
            mensaje = ""
        )
    }

    fun verificarRespuesta(nombre: String) {
        val correcta = _state.value.iglesiaCorrecta ?: return
        
        if (nombre == correcta.nombre) {
            _state.update { 
                it.copy(
                    juegoTerminado = true,
                    zoomLevel = 1f,
                    mensaje = "¡Enhorabuena has Ganado, era ${correcta.nombre}!"
                )
            }
        } else {
            _state.update { 
                it.copy(
                    zoomLevel = (it.zoomLevel - 1f).coerceAtLeast(1.2f),
                    opcionesDeshabilitadas = it.opcionesDeshabilitadas + nombre
                )
            }
        }
    }
}
