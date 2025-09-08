package com.dominik.mathtutorai.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.dominik.mathtutorai.model.Profile
import com.dominik.mathtutorai.profile.ProfileManager
import org.junit.Rule
import org.junit.Test


class ProfileSelectionScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileList_shouldDisplayProfiles() {
        val manager = ProfileManager().apply {
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
