package com.iessanalberto.iglesiasdeserrablo.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.iglesiasdeserrablo.components.IglesiaCard
import com.iessanalberto.iglesiasdeserrablo.data.listaIglesias

@Composable
fun MainScreen(navController: NavController ){
    Scaffold(modifier = Modifier.fillMaxSize()){
            innerPadding ->
        MainBodyContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MainBodyContent(navController: NavController, modifier: Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        items(listaIglesias) { iglesia ->
            IglesiaCard(iglesia = iglesia, modifier = modifier, onClick = {})
        }
    }

}