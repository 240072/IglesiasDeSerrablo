package com.iessanalberto.iglesiasdeserrablo.navigation

sealed class AppScreens (val route: String) {
    object MainScreen: AppScreens (route = "main_screen")
}