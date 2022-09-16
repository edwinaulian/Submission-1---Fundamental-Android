package com.storyApp.edwin.mainStoryApp.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.File

data class AddNewStoryRequest(
    @SerializedName("description")
    @Expose
    var email: String? = null,

    @SerializedName("photo")
    @Expose
    var password: File? = null
)
