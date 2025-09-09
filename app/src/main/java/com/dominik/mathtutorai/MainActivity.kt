package com.dominik.mathtutorai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.dominik.mathtutorai.profile.ProfileManager
import com.dominik.mathtutorai.ui.screens.CreateProfileScreen
import com.dominik.mathtutorai.ui.screens.EditProfileScreen
import com.dominik.mathtutorai.ui.screens.HomeScreen
import com.dominik.mathtutorai.ui.screens.ProfileSelectionScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var profileManager: ProfileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileManager = ProfileManager(this)

        setContent {
            val navController = rememberNavController()
            var startDestination by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                val profiles = withContext(Dispatchers.IO) {
                    profileManager.getAllProfiles()
                }
                startDestination = if (profiles.isEmpty()) {
                    "createProfile"
                } else {
                    "selectProfile"
                }
            }

            if (startDestination != null) {
                NavHost(navController = navController, startDestination = startDestination!!) {
                    composable("selectProfile") {
                        ProfileSelectionScreen(
                            profileManager = profileManager,
                            onProfileSelected = { profile ->
                                profileManager.switchProfile(profile.id)
                                navController.navigate("home/${profile.name}") {
                                    popUpTo("selectProfile") { inclusive = true }
                                }
                            },
                            onCreateNewProfile = {
                                navController.navigate("createProfile")
                            }
                        )
                    }

                    composable("createProfile") {
                        CreateProfileScreen(
                            profileManager = profileManager,
                            onProfileCreated = { name ->
                                val active = profileManager.getActiveProfile()
                                navController.navigate("home/${active?.name ?: name}") {
                                    popUpTo("selectProfile") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("editProfile") {
                        EditProfileScreen(
                            profileManager = profileManager,
                            onProfileUpdated = {
                                val active = profileManager.getActiveProfile()
                                navController.navigate("home/${active?.name ?: "Użytkowniku"}") {
                                    popUpTo("editProfile") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(
                        route = "home/{profileName}",
                        arguments = listOf(navArgument("profileName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("profileName") ?: "Użytkowniku"
                        HomeScreen(profileName = name, navController = navController)
                    }
                }
            }
        }
    }
}
