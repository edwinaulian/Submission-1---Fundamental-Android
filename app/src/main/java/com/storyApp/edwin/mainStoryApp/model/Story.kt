package com.storyApp.edwin.mainStoryApp.model

data class Story (
    val error: Boolean,
    val message: String,
    val listStory: ArrayList<ListStory>
)
