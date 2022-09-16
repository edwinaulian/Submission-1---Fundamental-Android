package com.storyApp.edwin.mainStoryApp.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddandRegisterResponse(
    @SerializedName("error")
    @Expose
    var error: Boolean? = false,

    @SerializedName("message")
    @Expose
    var message: String? = null,
)
