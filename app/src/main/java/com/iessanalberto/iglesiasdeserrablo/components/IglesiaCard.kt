package com.iessanalberto.iglesiasdeserrablo.components



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iessanalberto.iglesiasdeserrablo.models.Iglesia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IglesiaCard(iglesia: Iglesia,
                modifier: Modifier = Modifier,
                onClick:()->Unit
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(){onClick()},
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // FILA SUPERIOR (foto + título)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Image(
                    painter = painterResource(iglesia.foto),
                    contentDescription = "Foto iglesia",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))
*/
                Text(
                    text = iglesia.nombre,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            // DESCRIPCIÓN ABAJO
            Text(
                text = iglesia.descripcionShort,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}