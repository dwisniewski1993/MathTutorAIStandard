package com.dominik.mathtutorai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dominik.mathtutorai.model.Profile
import com.dominik.mathtutorai.profile.ProfileManager

@Composable
fun ProfileSelectionScreen(
    profileManager: ProfileManager,
    onProfileSelected: (Profile) -> Unit,
    onCreateNewProfile: () -> Unit
) {
    val profiles by remember { mutableStateOf(profileManager.getAllProfiles()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Wybierz profil", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(profiles) { profile ->
                Button(
                    onClick = {
                        profileManager.switchProfile(profile.id)
                        onProfileSelected(profile)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${profile.name} (${profile.age} lat)")
                }
            }
        }

        Button(onClick = onCreateNewProfile) {
            Text("➕ Dodaj nowy profil")
        }
    }
}
