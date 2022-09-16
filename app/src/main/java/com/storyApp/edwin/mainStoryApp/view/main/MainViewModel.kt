package com.storyApp.edwin.mainStoryApp.view.main

import android.util.Log
import androidx.lifecycle.*
import com.storyApp.edwin.mainStoryApp.model.ListStory
import com.storyApp.edwin.mainStoryApp.model.Story
import com.storyApp.edwin.mainStoryApp.model.UserModel
import com.storyApp.edwin.mainStoryApp.model.UserPreference
import com.storyApp.edwin.mainStoryApp.service.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: UserPreference) : ViewModel() {

    val listStory = MutableLiveData<ArrayList<ListStory>>()

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    fun getAllStory(token: String) {
        val client = RetrofitClient.create(token).getAllStories()
        client.enqueue(object : Callback<Story> {
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) {
                    listStory.postValue(response.body()?.listStory)
                    Log.d("halo", "${response.body()?.listStory}")
                }
            }
            override fun onFailure(call: Call<Story>, t: Throwable) {
                Log.d(FAILURE, "${t.message}")
            }
        })
    }

    companion object {
        private const val FAILURE = "Failure"
    }

}