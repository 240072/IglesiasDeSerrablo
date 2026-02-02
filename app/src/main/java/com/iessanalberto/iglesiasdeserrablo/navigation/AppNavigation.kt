package com.iessanalberto.iglesiasdeserrablo.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.iglesiasdeserrablo.screens.IglesiaScreen
import com.iessanalberto.iglesiasdeserrablo.screens.MainScreen
import com.iessanalberto.iglesiasdeserrablo.viewmodels.IglesiaViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {

    val iglesiaViewModel: IglesiaViewModel = viewModel()
    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route
    ) {

        composable(
            route = AppScreens.MainScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(700)
                ) + fadeIn(animationSpec = tween(300))
            }
        ) {
            MainScreen(
                navController = navController,
                iglesiaViewModel = iglesiaViewModel
            )
        }

        composable(
            route = AppScreens.IglesiaScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(700, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(400))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(700, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            IglesiaScreen(
                navController = navController,
                iglesiaViewModel = iglesiaViewModel
            )
        }
    }
}