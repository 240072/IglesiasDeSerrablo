package com.iessanalberto.iglesiasdeserrablo.components



import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias
import com.iessanalberto.iglesiasdeserrablo.models.Iglesia
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import android.graphics.Color as AndroidColor // Para el color de la línea

@Composable
fun MapWidget(
    iglesias: List<Iglesia>,
    dibujarRuta: Boolean,
    onIglesiaClick: (Iglesia) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Configuración obligatoria de osmdroid
    Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
    Configuration.getInstance().userAgentValue = context.packageName

    val mapView = remember { MapView(context) }

    DisposableEffect(mapView) {
        onDispose { mapView.onDetach() }
    }

    AndroidView(
        modifier = modifier,
        factory = { mapView },
        update = { view ->
            // Limpiamos overlays anteriores para que no se dupliquen al cambiar de ruta
            view.overlays.clear()

            view.setTileSource(TileSourceFactory.MAPNIK)
            view.setMultiTouchControls(true)
            view.controller.setZoom(11.0)
            view.controller.setCenter(GeoPoint(42.518, -0.364))

            // 1. Añadir Marcadores
            iglesias.forEach { iglesia ->
                val marker = Marker(view).apply {
                    position = GeoPoint(iglesia.latitud, iglesia.longitud)
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    title = iglesia.nombre
                    snippet = iglesia.descripcionShort
                    setOnMarkerClickListener { _, _ ->
                        onIglesiaClick(iglesia)
                        true
                    }
                }
                view.overlays.add(marker)
            }

            // 2. Añadir Líneas (Polyline) si está activo y hay más de 1 iglesia
            if (dibujarRuta && iglesias.size > 1) {
                val line = Polyline(view).apply {
                    // Mapeamos la lista de iglesias a puntos geográficos
                    val geoPoints = iglesias.map { GeoPoint(it.latitud, it.longitud) }
                    setPoints(geoPoints)
                    // Configuramos el estilo de la línea
                    color = AndroidColor.parseColor("#6D4C41") // Color marrón de tu app
                    width = 8.0f // Grosor de la línea
                }
                view.overlays.add(line)
            }

            // Forzar al mapa a redibujarse con los nuevos cambios
            view.invalidate()
        }
    )
}