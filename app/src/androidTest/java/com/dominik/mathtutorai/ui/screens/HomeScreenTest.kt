package com.dominik.mathtutorai.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_shouldDisplayGreetingWithName() {
        val testName = "Dominik"

        composeTestRule.setContent {
            HomeScreen(profileName = testName)
        }

        composeTestRule
            .onNodeWithText("Witaj, $testName! 🚀")
            .assertExists()
    }
}
