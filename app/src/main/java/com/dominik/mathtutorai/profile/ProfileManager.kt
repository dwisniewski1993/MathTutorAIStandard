package com.dominik.mathtutorai.profile

import com.dominik.mathtutorai.model.Profile
import java.util.UUID

class ProfileManager {

    private val profiles = mutableListOf<Profile>()
    private var activeProfileId: String? = null

    fun createProfile(name: String, age: Int, targetLevel: Int): Profile {
        val profile = Profile(
            id = UUID.randomUUID().toString(),
            name = name,
            age = age,
            targetLevel = targetLevel
        )
        profiles.add(profile)
        if (activeProfileId == null) activeProfileId = profile.id
        return profile
    }

    fun getActiveProfile(): Profile? {
        return profiles.find { it.id == activeProfileId }
    }

    fun switchProfile(id: String): Boolean {
        return if (profiles.any { it.id == id }) {
            activeProfileId = id
            true
        } else false
    }

    fun getAllProfiles(): List<Profile> = profiles.toList()
}
