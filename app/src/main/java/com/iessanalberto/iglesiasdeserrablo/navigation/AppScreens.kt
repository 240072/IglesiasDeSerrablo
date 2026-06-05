package com.iessanalberto.iglesiasdeserrablo.navigation

sealed class AppScreens (val route: String) {
    object MainScreen: AppScreens (route = "main_screen")

    object  IglesiaScreen: AppScreens (route = "iglesia_screen")
    object  MapScreen: AppScreens (route = "map_screen")
    object  GameScreen: AppScreens (route = "game_screen")
    object  PdfScreen: AppScreens (route = "pdf_screen")
}
