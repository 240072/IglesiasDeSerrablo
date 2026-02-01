package com.iessanalberto.iglesiasdeserrablo.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.iglesiasdeserrablo.R
import com.iessanalberto.iglesiasdeserrablo.components.IglesiaCard
import com.iessanalberto.iglesiasdeserrablo.components.RomanesqueWindowShape
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.navigation.AppScreens
import com.iessanalberto.iglesiasdeserrablo.viewmodels.IglesiaViewModel

@Composable
fun MainScreen(navController: NavController,
               iglesiaViewModel: IglesiaViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()){
            innerPadding ->
        MainBodyContent(
            navController = navController,
            iglesiaViewModel =iglesiaViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MainBodyContent(navController: NavController,
                    iglesiaViewModel: IglesiaViewModel,
                    modifier: Modifier) {

    // Safe area real (status bar, notch, etc)
    val insets = WindowInsets.systemBars.asPaddingValues()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(insets), // 👈 SafeView real tipo React Native
        color = Color.Transparent
    ) {

        // Fondo de piedra (imagen real)
        Image(
            painter = painterResource(id = R.drawable.stone_wall),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Ventana románica
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.95f),
            shape = RomanesqueWindowShape(),
            color = Color(0xFFF3E9D2),
            tonalElevation = 8.dp,
            shadowElevation = 16.dp,
            border = BorderStroke(4.dp, Color(0xFF6D4C41))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 32.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔥 LOGO
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(130.dp)
                        .padding(bottom = 16.dp)
                )

                // 📜 LISTA
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listaIglesias) { iglesia ->
                        IglesiaCard(
                            iglesia = iglesia,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                iglesiaViewModel.iglesiaSelected(iglesia.nombre)
                                navController.navigate(AppScreens.IglesiaScreen.route)
                            }
                        )
                    }
                }
            }
        }
    }
}


