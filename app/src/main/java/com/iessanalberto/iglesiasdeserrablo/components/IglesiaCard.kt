package com.iessanalberto.iglesiasdeserrablo.components



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iessanalberto.iglesiasdeserrablo.models.Iglesia
import com.iessanalberto.iglesiasdeserrablo.ui.theme.UncialAntiqua
import com.iessanalberto.iglesiasdeserrablo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IglesiaCard(
    iglesia: Iglesia,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        shape = RoundedCornerShape(14.dp)
    ) {

        // Fondo con imagen
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.stone_wall),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // Overlay oscuro para que el texto se lea bien
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x99000000))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // FILA SUPERIOR
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Imagen desde URL
                    AsyncImage(
                        model = iglesia.foto,   // 👈 AQUÍ la URL real
                        contentDescription = "Foto iglesia",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color(0xFFD6C6A1), RoundedCornerShape(10.dp))
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = iglesia.nombre,
                        fontFamily = UncialAntiqua,
                        fontSize = 20.sp,
                        color = Color(0xFFF5E6C8),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // DESCRIPCIÓN
                Text(
                    text = iglesia.descripcionShort,
                    fontFamily = UncialAntiqua,
                    fontSize = 14.sp,
                    color = Color(0xFFE6D8B8),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}