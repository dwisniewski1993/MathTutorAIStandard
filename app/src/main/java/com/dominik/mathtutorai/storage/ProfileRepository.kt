package com.dominik.mathtutorai.storage

import android.content.Context
import com.dominik.mathtutorai.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class ProfileRepository(context: Context) {

    private val dao = ProfileDatabase.getInstance(context).profileDao()

    suspend fun getAllProfiles(): List<Profile> = withContext(Dispatchers.IO) {
        dao.getAll().map { it.toModel() }
    }

    suspend fun getProfileById(id: String): Profile? = withContext(Dispatchers.IO) {
        dao.getById(id)?.toModel()
    }

    suspend fun saveProfile(profile: Profile) = withContext(Dispatchers.IO) {
        dao.insert(profile.toEntity())
    }

    suspend fun deleteProfile(profile: Profile) = withContext(Dispatchers.IO) {
        dao.delete(profile.toEntity())
    }

    suspend fun createProfile(name: String, age: Int, targetLevel: Int): Profile = withContext(Dispatchers.IO) {
        val profile = Profile(
            id = UUID.randomUUID().toString(),
            name = name,
            age = age,
            targetLevel = targetLevel
        )
        dao.insert(profile.toEntity())
        profile
    }

    private fun ProfileEntity.toModel(): Profile {
        return Profile(id, name, age, targetLevel)
    }

    private fun Profile.toEntity(): ProfileEntity {
        return ProfileEntity(id, name, age, targetLevel)
    }
}
