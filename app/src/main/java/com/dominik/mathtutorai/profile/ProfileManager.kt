package com.dominik.mathtutorai.profile

import android.content.Context
import com.dominik.mathtutorai.model.Profile
import com.dominik.mathtutorai.storage.ProfileRepository
import kotlinx.coroutines.runBlocking

class ProfileManager(context: Context) {

    private val repository = ProfileRepository(context)
    private var activeProfile: Profile? = null

    fun getAllProfiles(): List<Profile> = runBlocking {
        repository.getAllProfiles()
    }

    fun getActiveProfile(): Profile? = activeProfile

    fun switchProfile(id: String) {
        activeProfile = runBlocking {
            repository.getProfileById(id)
        }
    }

    fun createProfile(name: String, age: Int, targetLevel: Int): Profile {
        val profile = runBlocking {
            repository.createProfile(name, age, targetLevel)
        }
        activeProfile = profile
        return profile
    }

    fun saveProfile(profile: Profile) {
        runBlocking {
            repository.saveProfile(profile)
        }
    }

    fun deleteProfile(profile: Profile) {
        runBlocking {
            repository.deleteProfile(profile)
            if (activeProfile?.id == profile.id) {
                activeProfile = null
            }
        }
    }
}
