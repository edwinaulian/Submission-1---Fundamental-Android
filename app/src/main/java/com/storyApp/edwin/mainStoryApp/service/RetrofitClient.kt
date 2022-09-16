package com.storyApp.edwin.mainStoryApp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitClient {
    companion object {
        private const val BASE_URL = "https://story-api.dicoding.dev/v1/"
        fun create(accessToken: String): Api {
            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor("Bearer", accessToken))
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(Api::class.java)
        }

        fun loginVal(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiInstance = retrofit.create(Api::class.java)
            return apiInstance
        }
    }
}