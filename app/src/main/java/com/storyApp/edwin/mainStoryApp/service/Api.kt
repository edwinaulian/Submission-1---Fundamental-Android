package com.storyApp.edwin.mainStoryApp.service

import com.storyApp.edwin.mainStoryApp.model.*
import com.storyApp.edwin.mainStoryApp.model.request.AddNewStoryRequest
import com.storyApp.edwin.mainStoryApp.model.request.LoginRequest
import com.storyApp.edwin.mainStoryApp.model.request.RegisterRequest
import com.storyApp.edwin.mainStoryApp.model.response.AddandRegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("stories")
    fun getAllStories(): Call<Story>

    @POST("stories")
    fun postNewStory(@Body addNewStoryRequest: AddNewStoryRequest): Call<AddandRegisterResponse>

    @POST("register")
    fun register(@Body registerRequest: RegisterRequest) : Call<AddandRegisterResponse>

    @POST("login")
    fun login(@Body userRequest: LoginRequest): Call<LoginResponse>
}