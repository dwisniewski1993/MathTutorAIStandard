package com.dominik.mathtutorai.ui.screens

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.dominik.mathtutorai.profile.ProfileManager
import org.junit.Rule
import org.junit.Test

class CreateProfileScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test
    fun shouldDisplayFormFieldsAndSaveButton() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val profileManager = ProfileManager(context)

        composeTestRule.setContent {
            CreateProfileScreen(profileManager = profileManager, onProfileCreated = {})
        }

        composeTestRule.onNodeWithText("Imię").assertExists()
        composeTestRule.onNodeWithText("Wiek").assertExists()
        composeTestRule.onNodeWithText("Poziom docelowy").assertExists()
        composeTestRule.onNodeWithText("Zapisz profil").assertExists()
    }
}
