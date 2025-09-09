package com.dominik.mathtutorai.ui.screens

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.dominik.mathtutorai.profile.ProfileManager
import org.junit.Rule
import org.junit.Test

class ProfileSelectionScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun profileList_shouldDisplayProfiles() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = ProfileManager(context).apply {
            createProfile("Dominik", 12, 3)
            createProfile("Ola", 10, 2)
        }

        composeTestRule.setContent {
            ProfileSelectionScreen(
                profileManager = manager,
                onProfileSelected = {},
                onCreateNewProfile = {}
            )
        }

        composeTestRule.onNodeWithText("Dominik (12 lat)").assertExists()
        composeTestRule.onNodeWithText("Ola (10 lat)").assertExists()
        composeTestRule.onNodeWithText("➕ Dodaj nowy profil").assertExists()
    }
}
