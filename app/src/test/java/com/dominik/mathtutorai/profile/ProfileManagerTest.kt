package com.dominik.mathtutorai.profile

import com.dominik.mathtutorai.model.Difficulty
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProfileManagerTest {

    private lateinit var manager: ProfileManager

    @Before
    fun setup() {
        manager = ProfileManager()
    }

    @Test
    fun `should create and retrieve profile`() {
        val profile = manager.createProfile("Dominik", 12, 3)
        val active = manager.getActiveProfile()
        assertNotNull(active)
        assertEquals("Dominik", active?.name)
        assertEquals(3, active?.targetLevel)
    }

    @Test
    fun `should switch between profiles`() {
        val p1 = manager.createProfile("Ala", 10, 2)
        val p2 = manager.createProfile("Olek", 11, 4)

        val switched = manager.switchProfile(p2.id)
        assertTrue(switched)
        assertEquals("Olek", manager.getActiveProfile()?.name)
    }

    @Test
    fun `should return false when switching to non-existent profile`() {
        val result = manager.switchProfile("non-existent-id")
        assertFalse(result)
    }
}
