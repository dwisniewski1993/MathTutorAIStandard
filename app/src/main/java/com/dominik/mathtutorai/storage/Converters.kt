package com.dominik.mathtutorai.storage

import androidx.room.TypeConverter
import com.dominik.mathtutorai.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromMasteryMap(value: Map<String, Float>): String = gson.toJson(value)

    @TypeConverter
    fun toMasteryMap(value: String): Map<String, Float> {
        val type = object : TypeToken<Map<String, Float>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPreferences(value: LearningPreferences): String = gson.toJson(value)

    @TypeConverter
    fun toPreferences(value: String): LearningPreferences =
        gson.fromJson(value, LearningPreferences::class.java)

    @TypeConverter
    fun fromHistory(value: List<SessionRecord>): String = gson.toJson(value)

    @TypeConverter
    fun toHistory(value: String): List<SessionRecord> {
        val type = object : TypeToken<List<SessionRecord>>() {}.type
        return gson.fromJson(value, type)
    }
}
