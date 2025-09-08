package com.dominik.mathtutorai.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.dominik.mathtutorai.profile.ProfileManager
import org.junit.Rule
import org.junit.Test

class CreateProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayFormFieldsAndSaveButton() {
        composeTestRule.setContent {
            CreateProfileScreen(profileManager = ProfileManager(), onProfileCreated = {})
        }

        composeTestRule.onNodeWithText("Imię").assertExists()
        composeTestRule.onNodeWithText("Wiek").assertExists()
        composeTestRule.onNodeWithText("Poziom docelowy").assertExists()
        composeTestRule.onNodeWithText("Zapisz profil").assertExists()
    }
}
