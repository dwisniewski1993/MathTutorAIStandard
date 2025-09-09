package com.dominik.mathtutorai.storage

import com.dominik.mathtutorai.model.Profile
import com.dominik.mathtutorai.storage.ProfileEntity

fun ProfileEntity.toDomain(): Profile = Profile(
    id = id,
    name = name,
    age = age,
    targetLevel = targetLevel
)

fun Profile.toEntity(): ProfileEntity = ProfileEntity(
    id = id,
    name = name,
    age = age,
    targetLevel = targetLevel
)
