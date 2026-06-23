package com.iessanalberto.iglesiasdeserrablo.models

data class Ruta (
    var nombre: String,
    val iglesiasEnRuta : List<Iglesia>
)
