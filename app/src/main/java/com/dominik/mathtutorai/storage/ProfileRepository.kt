package com.dominik.mathtutorai.storage

import android.content.Context
import com.dominik.mathtutorai.model.Profile

class ProfileRepository(context: Context) {

    private val db = ProfileDatabase.getInstance(context)
    private val dao = db.profileDao()

    suspend fun createProfile(name: String, age: Int, targetLevel: Int): Profile {
        val entity = ProfileEntity(name = name, age = age, targetLevel = targetLevel)
        dao.insert(entity)
        return entity.toDomain()
    }

    suspend fun getProfileById(id: String): Profile? {
        return dao.getById(id)?.toDomain()
    }

    suspend fun getAllProfiles(): List<Profile> {
        return dao.getAll().map { it.toDomain() }
    }

    suspend fun deleteProfile(profile: Profile) {
        dao.delete(profile.toEntity())
    }

    suspend fun updateProfile(profile: Profile) {
        dao.update(profile.toEntity())
    }
}
