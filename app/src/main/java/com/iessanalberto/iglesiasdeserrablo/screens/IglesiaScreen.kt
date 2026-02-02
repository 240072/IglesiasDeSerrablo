package com.iessanalberto.iglesiasdeserrablo.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.iessanalberto.iglesiasdeserrablo.R
import com.iessanalberto.iglesiasdeserrablo.components.RomanesqueWindowShape
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.ui.theme.UncialAntiqua
import com.iessanalberto.iglesiasdeserrablo.viewmodels.IglesiaViewModel


@Composable
fun IglesiaScreen(
    navController: NavController,
    iglesiaViewModel: IglesiaViewModel
) {

    val state by iglesiaViewModel.iglesiaState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(state.nombre) {
        iglesiaViewModel.cargarIglesia()
    }

    val insets = WindowInsets.systemBars.asPaddingValues()

    Surface(
        modifier = Modifier.fillMaxSize()
        .padding(insets),
        color = Color(0xFF1E1E1E)
    ) {
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
                    .verticalScroll(state = scrollState)
                    .padding(20.dp),


                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(130.dp)
                        .padding(bottom = 16.dp)
                )

                LazyRow() {
                    items(state.listaFotos) { foto ->
                        AsyncImage(
                            model = foto,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(305.dp)
                                .padding(20.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }



                Text(
                    text = state.nombre,
                    fontFamily = UncialAntiqua,
                    fontSize = 30.sp,
                    color = Color.Black
                )

                Text(
                    text = state.descripcionLong,
                    fontFamily = UncialAntiqua,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(iglesiaViewModel.iglesiaState.value.ubicación)
                        )
                        navController.context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D4C41),      // marrón románico
                        contentColor = Color(0xFFF3E9D2)         // texto piedra clara
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 2.dp
                    )
                ) {
                    Text(
                        text = "Ver ubicación en Google Maps",
                        fontFamily = UncialAntiqua,
                        fontSize = 18.sp
                    )
                }

                Text(
                    text = state.descripcionTotal,
                    fontFamily = UncialAntiqua,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {

                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.Black
                        )
                    }

                    Text(
                        text = "Volver",
                        fontFamily = UncialAntiqua,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}