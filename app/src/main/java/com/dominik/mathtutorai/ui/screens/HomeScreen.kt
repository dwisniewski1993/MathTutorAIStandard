package com.dominik.mathtutorai.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(profileName: String) {
    Surface {
        Text("Witaj, $profileName! 🚀", style = MaterialTheme.typography.headlineMedium)
    }
}
