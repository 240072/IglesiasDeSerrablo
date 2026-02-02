package com.iessanalberto.iglesiasdeserrablo.states

data class IglesiaState(
    val nombre: String = " ",
    val descripcionLong: String = " ",
    val descripcionShort: String = " ",
    val foto: String = " ",
    val ubicación: String = " ",
    val listaFotos: List<String> = listOf(),
    val descripcionTotal: String = " "
)
