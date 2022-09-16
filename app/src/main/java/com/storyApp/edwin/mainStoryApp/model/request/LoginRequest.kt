package com.storyApp.edwin.mainStoryApp.model.request

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class LoginRequest (
    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("password")
    @Expose
    var password: String? = null
)

