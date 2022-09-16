package com.storyApp.edwin.mainStoryApp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.storyApp.edwin.mainStoryApp.model.UserPreference
import com.storyApp.edwin.mainStoryApp.view.login.LoginViewModel
import com.storyApp.edwin.mainStoryApp.view.main.MainViewModel
import com.storyApp.edwin.mainStoryApp.view.signup.SignupViewModel

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}