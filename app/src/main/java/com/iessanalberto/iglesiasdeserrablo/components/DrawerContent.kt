package com.iessanalberto.iglesiasdeserrablo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.twotone.Church
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iessanalberto.iglesiasdeserrablo.R
import com.iessanalberto.iglesiasdeserrablo.navigation.AppScreens
import com.iessanalberto.iglesiasdeserrablo.ui.theme.UncialAntiqua

@Composable
fun DrawerContent(
    navController: NavController,
    onClose: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Color(0xFFF3E9D2))
            .padding(16.dp)
    ) {


        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(120.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(24.dp))


        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            DrawerItem("Inicio") {
                navController.navigate(AppScreens.MainScreen.route) {
                    popUpTo(0)
                }
                onClose()
            }

            // Icono superpuesto
            Icon(
                imageVector = Icons.TwoTone.Church,
                contentDescription = "Inicio",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }


        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            DrawerItem("Mapa Rutas") {
                onClose()
            }

            // Icono superpuesto
            Icon(
                imageVector = Icons.Filled.Map,  // 👈 Icono de mapa
                contentDescription = "Mapa Rutas",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }


        DrawerItem("Sobre Serrablo") {
            onClose()
        }


        DrawerItem("Contacto") {
            onClose()
        }


        Spacer(modifier = Modifier.weight(1f))


        Text(
            text = "© Iglesias de Serrablo",
            fontFamily = UncialAntiqua,
            fontSize = 14.sp,
            color = Color(0xFF6D4C41),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun DrawerItem(title: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color(0xFF6D4C41)
        )
    ) {
        Text(
            text = title,
            fontFamily = UncialAntiqua,
            fontSize = 18.sp
        )
    }
}