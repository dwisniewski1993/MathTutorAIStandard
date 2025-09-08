package com.dominik.mathtutorai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dominik.mathtutorai.profile.ProfileManager

@Composable
fun CreateProfileScreen(
    profileManager: ProfileManager,
    onProfileCreated: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Nowy profil", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Imię") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Wiek") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("Poziom docelowy") },
            modifier = Modifier.fillMaxWidth()
        )

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                val ageInt = age.toIntOrNull()
                val levelInt = level.toIntOrNull()

                if (name.isBlank() || ageInt == null || ageInt <= 0 || levelInt == null || levelInt !in 1..10) {
                    error = "Wprowadź poprawne dane"
                } else {
                    val profile = profileManager.createProfile(name, ageInt, levelInt)
                    onProfileCreated(profile.name)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zapisz profil")
        }
    }
}
