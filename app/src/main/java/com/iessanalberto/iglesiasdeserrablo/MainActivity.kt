package com.iessanalberto.iglesiasdeserrablo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iessanalberto.iglesiasdeserrablo.navigation.AppNavigation
import com.iessanalberto.iglesiasdeserrablo.ui.theme.IglesiasDeSerrabloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IglesiasDeSerrabloTheme {
                AppNavigation()
            }
        }
    }
}

