package com.iessanalberto.iglesiasdeserrablo.components

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.iessanalberto.iglesiasdeserrablo.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun AudioMediaPlayer(
    audioId: Int
) {

    val context = LocalContext.current

    val mediaPlayer = remember {
        MediaPlayer.create(context, audioId)
    }

    var reproduciendo by remember {
        mutableStateOf(false)
    }
    var posicion by remember {
        mutableIntStateOf(0)
    }
    val duracion = mediaPlayer.duration
    LaunchedEffect(mediaPlayer) {

        mediaPlayer.setOnCompletionListener {
            reproduciendo = false
            posicion = 0
            mediaPlayer.seekTo(0)
        }

        while (true) {

            if (mediaPlayer.isPlaying) {
                posicion = mediaPlayer.currentPosition
            }

            delay(200.milliseconds)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    reproduciendo = false
                } else {
                    mediaPlayer.start()
                    reproduciendo = true
                }
            },
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = if (reproduciendo) {
                    Icons.Default.Pause
                } else {
                    Icons.Default.PlayArrow
                },
                contentDescription = if (reproduciendo) {
                    "Pausar"
                } else {
                    "Reproducir"
                },
                modifier = Modifier.size(40.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {



            Slider(
                value = if (duracion > 0)
                    posicion.toFloat() / duracion
                else
                    0f,
                onValueChange = {
                    posicion = (it * duracion).toInt()
                },
                onValueChangeFinished = {
                    mediaPlayer.seekTo(posicion)
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(formatTime(posicion))
                Text(formatTime(duracion))
            }
        }
    }
}
@SuppressLint("DefaultLocale")
fun formatTime(milliseconds: Int): String {
    val seconds = milliseconds / 1000

    val minutes = seconds / 60
    val remainingSeconds = seconds % 60

    return String.format(
        "%02d:%02d",
        minutes,
        remainingSeconds
    )
}