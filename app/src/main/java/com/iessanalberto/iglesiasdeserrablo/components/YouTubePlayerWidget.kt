package com.iessanalberto.iglesiasdeserrablo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayerWidget(
    videoId: String, // El ID del video (Ej: "dQw4w9WgXcQ")
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { ctx ->
            // Inicializamos la vista del reproductor
            YouTubePlayerView(ctx).apply {
                // Sincronizar con el ciclo de vida para pausar automáticamente si el usuario sale de la app
                lifecycleOwner.lifecycle.addObserver(this)
                enableAutomaticInitialization = false

                // Configuración manual del IFrame para evitar fallos de carga aleatorios
                val iFramePlayerOptions = IFramePlayerOptions.Builder(
                    context = context
                )
                    .controls(1) // 1 muestra los controles por defecto de YT, 0 los oculta
                    .build()

                initialize(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // Cuando esté listo, cargará el video desde el segundo 0
                        youTubePlayer.cueVideo(videoId, 0f)
                        // Nota: si usas 'loadVideo' en vez de 'cueVideo', se reproducirá automáticamente al cargar
                    }
                }, iFramePlayerOptions)
            }
        }
    )
}