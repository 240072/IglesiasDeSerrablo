package com.iessanalberto.iglesiasdeserrablo.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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
    // Sin Scaffold, manejamos todo manualmente
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars) // 👈 Safe area completo
            .background(Color.Black)
    ) {
        MainBodyContent(
            navController = navController,
            iglesiaViewModel = iglesiaViewModel,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MainBodyContent(
    navController: NavController,
    iglesiaViewModel: IglesiaViewModel,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        // Fondo de piedra
        Image(
            painter = painterResource(id = R.drawable.stone_wall),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Ventana románica - ahora más cerca del top
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .fillMaxHeight(0.97f)
                .align(Alignment.Center)
                .padding(top = 8.dp), // 👈 Pequeño margen del top
            shape = RomanesqueWindowShape(),
            color = Color(0xFFF3E9D2),
            tonalElevation = 12.dp,
            shadowElevation = 20.dp,
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
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
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