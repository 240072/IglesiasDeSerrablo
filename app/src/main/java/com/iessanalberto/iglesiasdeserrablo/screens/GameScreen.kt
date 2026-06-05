package com.iessanalberto.iglesiasdeserrablo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.iessanalberto.iglesiasdeserrablo.R
import com.iessanalberto.iglesiasdeserrablo.components.RomanesqueWindowShape
import com.iessanalberto.iglesiasdeserrablo.ui.theme.UncialAntiqua
import com.iessanalberto.iglesiasdeserrablo.viewmodels.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController) {
    val quizViewModel: QuizViewModel = viewModel()
    val state by quizViewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1E1E1E)
    ) {
        Image(
            painter = painterResource(id = R.drawable.stone_wall),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.95f)
                .padding(vertical = 16.dp, horizontal = 8.dp),
            shape = RomanesqueWindowShape(),
            color = Color(0xFFF3E9D2),
            tonalElevation = 8.dp,
            shadowElevation = 16.dp,
            border = BorderStroke(4.dp, Color(0xFF6D4C41))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Cabecera con botón volver
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color(0xFF6D4C41)
                        )
                    }
                    Text(
                        text = "Adivina la Iglesia",
                        fontFamily = UncialAntiqua,
                        fontSize = 24.sp,
                        color = Color(0xFF6D4C41),
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }

                // Imagen con zoom
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(3.dp, Color(0xFF6D4C41), RoundedCornerShape(16.dp))
                ) {
                    AsyncImage(
                        model = state.iglesiaCorrecta?.foto,
                        contentDescription = "Imagen misteriosa",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = state.zoomLevel,
                                scaleY = state.zoomLevel
                            )
                    )
                }

                Text(
                    text = "¿Qué Iglesia es?",
                    fontFamily = UncialAntiqua,
                    fontSize = 22.sp,
                    color = Color.Black
                )

                // Mensaje de éxito
                if (state.juegoTerminado) {
                    Text(
                        text = state.mensaje,
                        fontFamily = UncialAntiqua,
                        fontSize = 20.sp,
                        color = Color(0xFF2E7D32),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Button(
                        onClick = { quizViewModel.reiniciarJuego() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text("Jugar de nuevo", fontFamily = UncialAntiqua)
                    }
                }

                // Opciones de respuesta
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    state.opciones.forEach { opcion ->
                        val isCorrect = state.juegoTerminado && opcion.nombre == state.iglesiaCorrecta?.nombre
                        val isDisabled = state.opcionesDeshabilitadas.contains(opcion.nombre) || state.juegoTerminado

                        Button(
                            onClick = { quizViewModel.verificarRespuesta(opcion.nombre) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isDisabled || isCorrect,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isCorrect) Color(0xFF2E7D32) else Color(0xFF6D4C41),
                                disabledContainerColor = if (isDisabled) Color.Gray.copy(alpha = 0.5f) else Color(0xFF6D4C41)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = opcion.nombre,
                                fontFamily = UncialAntiqua,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
