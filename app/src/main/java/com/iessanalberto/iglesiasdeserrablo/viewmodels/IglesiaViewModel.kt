package com.iessanalberto.iglesiasdeserrablo.viewmodels

import androidx.lifecycle.ViewModel
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.states.IglesiaState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IglesiaViewModel : ViewModel() {

    private val _iglesiaState = MutableStateFlow(IglesiaState())
    val iglesiaState: StateFlow<IglesiaState> = _iglesiaState.asStateFlow()

    fun iglesiaSelected(nombre: String) {
        _iglesiaState.update { currentState ->
            currentState.copy(nombre = nombre)
        }
    }

    fun cargarIglesia() {
        val nombreSeleccionado = _iglesiaState.value.nombre

        val iglesia = listaIglesias.find { it.nombre == nombreSeleccionado }

        iglesia?.let { data ->
            _iglesiaState.update {
                it.copy(
                    nombre = data.nombre,
                    descripcionLong = data.descripcionLong,
                    descripcionShort = data.descripcionShort,
                    foto = data.foto,
                    ubicación = data.ubicación,
                    listaFotos = data.listaFotos,
                    descripcionTotal = data.descripcionTotal
                )
            }
        }
    }
}