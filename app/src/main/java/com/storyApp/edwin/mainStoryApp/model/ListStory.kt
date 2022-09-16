package com.storyApp.edwin.mainStoryApp.model

data class ListStory(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String,
    val lat: Float,
    val lon: Float
)
