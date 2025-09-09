package com.dominik.mathtutorai.profile

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dominik.mathtutorai.model.Profile
import com.dominik.mathtutorai.storage.ProfileDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProfileManagerTest {

    private lateinit var manager: ProfileManager
    private lateinit var db: ProfileDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProfileDatabase::class.java).build()
        manager = ProfileManager(context)
    }

    @After
    fun teardown() {
        runBlocking {
            db.profileDao().getAll().forEach { db.profileDao().delete(it) }
        }
        db.close()
    }

    @Test
    fun createProfile_shouldStoreAndActivateProfile() {
        val profile = manager.createProfile("Dominik", 12, 3)
        assertEquals("Dominik", profile.name)
        assertEquals(profile, manager.getActiveProfile())
    }

    @Test
    fun switchProfile_shouldChangeActiveProfile() {
        val p1 = manager.createProfile("Ala", 10, 2)
        val p2 = manager.createProfile("Olek", 11, 4)

        manager.switchProfile(p1.id)
        assertEquals(p1.id, manager.getActiveProfile()?.id)

        manager.switchProfile(p2.id)
        assertEquals(p2.id, manager.getActiveProfile()?.id)
    }

    @Test
    fun getAllProfiles_shouldReturnAllCreatedProfiles() {
        manager.createProfile("Ala", 10, 2)
        manager.createProfile("Olek", 11, 4)

        val all = manager.getAllProfiles()
        assertEquals(2, all.size)
    }

    @Test
    fun deleteProfile_shouldRemoveAndResetActive() {
        val profile = manager.createProfile("Test", 9, 1)
        manager.deleteProfile(profile)

        val all = manager.getAllProfiles()
        assertTrue(all.none { it.id == profile.id })
        assertNull(manager.getActiveProfile())
    }
}
