package com.dominik.mathtutorai.model

data class Profile(
    val id: String,
    val name: String,
    val age: Int,
    val targetLevel: Int,
    val mastery: Map<String, Float> = emptyMap(), // np. "mult_by_7" → 0.6
    val preferences: LearningPreferences = LearningPreferences(),
    val history: List<SessionRecord> = emptyList()
)

data class LearningPreferences(
    val preferredDifficulty: Difficulty = Difficulty.MEDIUM,
    val pace: Pace = Pace.NORMAL,
    val style: LearningStyle = LearningStyle.STANDARD
)

enum class Difficulty { EASY, MEDIUM, HARD }
enum class Pace { SLOW, NORMAL, FAST }
enum class LearningStyle { VISUAL, TEXTUAL, STANDARD }

data class SessionRecord(
    val timestamp: Long,
    val correct: Int,
    val incorrect: Int,
    val durationSeconds: Int
)
