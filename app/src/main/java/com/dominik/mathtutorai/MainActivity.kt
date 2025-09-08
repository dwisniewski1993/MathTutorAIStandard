package com.dominik.mathtutorai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.dominik.mathtutorai.profile.ProfileManager
import com.dominik.mathtutorai.ui.screens.CreateProfileScreen
import com.dominik.mathtutorai.ui.screens.HomeScreen
import com.dominik.mathtutorai.ui.screens.ProfileSelectionScreen

class MainActivity : ComponentActivity() {

    private val profileManager = ProfileManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "selectProfile") {
                composable("selectProfile") {
                    ProfileSelectionScreen(
                        profileManager = profileManager,
                        onProfileSelected = { profile ->
                            navController.navigate("home/${profile.name}")
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
                            navController.navigate("home/$name") {
                                popUpTo("selectProfile") { inclusive = true }
                            }
                        }
                    )
                }

                composable(
                    route = "home/{profileName}",
                    arguments = listOf(navArgument("profileName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("profileName") ?: "Użytkowniku"
                    HomeScreen(profileName = name)
                }
            }
        }
    }
}
