package com.storyApp.edwin.mainStoryApp.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class LoginResponse (
        @SerializedName("error")
        @Expose
        var error: Boolean? = false,

        @SerializedName("message")
        @Expose
        var message: String? = null,

        @SerializedName("loginResult")
        @Expose
        var loginResult: LoginList? = null,
)

data class LoginList (
    @SerializedName("userId")
    @Expose
    var userId: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("token")
    @Expose
    var token: String? = null
)