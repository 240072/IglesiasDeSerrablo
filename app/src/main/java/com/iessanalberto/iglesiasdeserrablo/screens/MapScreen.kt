package com.iessanalberto.iglesiasdeserrablo.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.iglesiasdeserrablo.components.MapWidget
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.navigation.AppScreens
import com.iessanalberto.iglesiasdeserrablo.ui.theme.UncialAntiqua
import com.iessanalberto.iglesiasdeserrablo.viewmodels.IglesiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController, iglesiaViewModel: IglesiaViewModel) {

    // Obtener las rutas únicas disponibles en tu lista de iglesias (Ej: "Ruta A", "Ruta B", "Todas")
    var rutaSeleccionada by remember { mutableStateOf("Todas las Iglesias") }

    val opcionesRutas =
        listOf("Todas las Iglesias") + listaIglesias.mapNotNull{it.ruta}.distinct()
    var expanded by remember { mutableStateOf(false) }

    // Filtrar la lista de iglesias dinámicamente según la selección
    val iglesiasFiltradas by remember {
        derivedStateOf {
            if (rutaSeleccionada == "Todas las Iglesias") {
                listaIglesias
            } else {
                listaIglesias.filter { rutaSeleccionada == it.ruta }
            }
        }
    }
    Log.d("Probando", iglesiasFiltradas.size.toString())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Ruta Iglesias Serrablo", fontFamily = UncialAntiqua, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6D4C41))
            )
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            MapWidget(
                iglesias = iglesiasFiltradas,
                // Si elige "Todas", quizás no quieras líneas cruzando todo el mapa, solo si elige una ruta específica
                dibujarRuta = rutaSeleccionada != "Todas las Iglesias",
                onIglesiaClick = { iglesia ->
                    iglesiaViewModel.iglesiaSelected(iglesia.nombre)
                    navController.navigate(AppScreens.IglesiaScreen.route)
                },
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            ) {
                // --- SELECTOR DE RUTAS (Dropdown Menu de Material 3) ---
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = rutaSeleccionada,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Selecciona una ruta") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        opcionesRutas.forEach { ruta ->
                            DropdownMenuItem(
                                text = { Text(ruta.toString()) },
                                onClick = {
                                    rutaSeleccionada = ruta.toString()
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
