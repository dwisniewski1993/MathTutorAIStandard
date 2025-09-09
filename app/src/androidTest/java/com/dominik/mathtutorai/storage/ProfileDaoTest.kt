package com.dominik.mathtutorai.storage

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dominik.mathtutorai.model.Profile
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileDaoTest {

    private lateinit var db: ProfileDatabase
    private lateinit var dao: ProfileDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProfileDatabase::class.java).build()
        dao = db.profileDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndGetProfileById() = runBlocking {
        val profile = ProfileEntity("id123", "Dominik", 12, 3)
        dao.insert(profile)

        val result = dao.getById("id123")
        Assert.assertNotNull(result)
        Assert.assertEquals("Dominik", result?.name)
    }

    @Test
    fun insertAndGetAllProfiles() = runBlocking {
        val p1 = ProfileEntity("id1", "Ala", 10, 2)
        val p2 = ProfileEntity("id2", "Olek", 11, 4)
        dao.insert(p1)
        dao.insert(p2)

        val all = dao.getAll()
        Assert.assertEquals(2, all.size)
    }

    @Test
    fun deleteProfile() = runBlocking {
        val profile = ProfileEntity("idDel", "Test", 9, 1)
        dao.insert(profile)

        dao.delete(profile)
        val result = dao.getById("idDel")
        Assert.assertNull(result)
    }
}
