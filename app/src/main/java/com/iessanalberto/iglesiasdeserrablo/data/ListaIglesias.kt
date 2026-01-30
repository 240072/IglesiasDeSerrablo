package com.iessanalberto.iglesiasdeserrablo.data




import com.iessanalberto.iglesiasdeserrablo.models.Iglesia

val listaIglesias = listOf(
    Iglesia(
        nombre = "Catedral de Sevilla",
        descripcionLong = "La Catedral de Sevilla es la catedral gótica más grande del mundo. Fue construida sobre la antigua mezquita almohade y alberga la tumba de Cristóbal Colón.",
        descripcionShort = "La catedral gótica más grande del mundo.",
        foto = 0
    ),
    Iglesia(
        nombre = "Basílica de la Sagrada Familia",
        descripcionLong = "La Sagrada Familia, diseñada por Antoni Gaudí, es uno de los templos más emblemáticos de Barcelona y sigue en construcción desde 1882.",
        descripcionShort = "La obra maestra inacabada de Gaudí.",
        foto = 1
    ),
    Iglesia(
        nombre = "Basílica del Pilar",
        descripcionLong = "La Basílica del Pilar es uno de los centros de peregrinación más importantes de España y está dedicada a la Virgen del Pilar.",
        descripcionShort = "Icono religioso de Zaragoza.",
        foto = 2
    ),
    Iglesia(
        nombre = "Catedral de Santiago de Compostela",
        descripcionLong = "Destino final del Camino de Santiago, esta catedral es uno de los lugares de peregrinación más importantes del cristianismo.",
        descripcionShort = "Final del Camino de Santiago.",
        foto = 3
    )
)