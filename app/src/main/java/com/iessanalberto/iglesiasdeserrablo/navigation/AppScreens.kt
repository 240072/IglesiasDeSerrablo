package com.iessanalberto.iglesiasdeserrablo.navigation

sealed class AppScreens (val route: String) {
    object MainScreen: AppScreens (route = "main_screen")

    object  IglesiaScreen: AppScreens (route = "iglesia_screen")
}