package com.iessanalberto.iglesiasdeserrablo.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.navigation.AppScreens
import com.iessanalberto.iglesiasdeserrablo.ui.theme.UncialAntiqua
import com.iessanalberto.iglesiasdeserrablo.viewmodels.IglesiaViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController, iglesiaViewModel: IglesiaViewModel) {
    val context = LocalContext.current

    // Configuración de osmdroid (necesaria para que funcione correctamente)
    Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
    Configuration.getInstance().userAgentValue = context.packageName

    val mapView = remember { MapView(context) }

    DisposableEffect(mapView) {
        onDispose {
            mapView.onDetach()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Ruta Iglesias Serrablo",
                        fontFamily = UncialAntiqua,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6D4C41)
                )
            )
        }
    ) { paddingValues ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            factory = {
                mapView.apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    controller.setZoom(11.0)
                    // Punto central de la ruta (Sabiñánigo aprox)
                    controller.setCenter(GeoPoint(42.518, -0.364))

                    // Añadir marcadores para cada iglesia
                    listaIglesias.forEach { iglesia ->
                        val marker = Marker(this)
                        marker.position = GeoPoint(iglesia.latitud, iglesia.longitud)
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        marker.title = iglesia.nombre
                        marker.snippet = iglesia.descripcionShort
                        
                        marker.setOnMarkerClickListener { _, _ ->
                            iglesiaViewModel.iglesiaSelected(iglesia.nombre)
                            navController.navigate(AppScreens.IglesiaScreen.route)
                            true
                        }
                        
                        overlays.add(marker)
                    }
                }
            }
        )
    }
}
