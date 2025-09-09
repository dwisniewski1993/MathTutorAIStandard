package com.dominik.mathtutorai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    profileName: String,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(profileName) },
                actions = {
                    TextButton(onClick = {
                        navController.navigate("editProfile")
                    }) {
                        Text("Edytuj profil")
                    }
                    TextButton(onClick = {
                        navController.navigate("selectProfile") {
                            popUpTo("home/$profileName") { inclusive = true }
                        }
                    }) {
                        Text("Zmień profil")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Witaj, $profileName!",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
