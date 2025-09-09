package com.dominik.mathtutorai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dominik.mathtutorai.model.Profile
import com.dominik.mathtutorai.profile.ProfileManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    profileManager: ProfileManager,
    onProfileUpdated: () -> Unit
) {
    val activeProfile = profileManager.getActiveProfile()
    var name by remember { mutableStateOf(activeProfile?.name ?: "") }
    var age by remember { mutableStateOf(activeProfile?.age?.toString() ?: "") }
    var level by remember { mutableStateOf(activeProfile?.targetLevel?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Edytuj profil", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Imię") }
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Wiek") }
        )

        OutlinedTextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("Poziom docelowy") }
        )

        Button(
            onClick = {
                val updated = activeProfile?.copy(
                    name = name,
                    age = age.toIntOrNull() ?: 0,
                    targetLevel = level.toIntOrNull() ?: 0
                )
                if (updated != null) {
                    profileManager.updateProfile(updated)
                    onProfileUpdated()
                }
            }
        ) {
            Text("Zapisz zmiany")
        }
    }
}
