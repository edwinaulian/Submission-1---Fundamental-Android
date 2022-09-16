package com.storyApp.edwin.mainStoryApp.view.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.storyApp.edwin.mainStoryApp.databinding.ActivitySignupBinding
import com.storyApp.edwin.mainStoryApp.model.UserModel
import com.storyApp.edwin.mainStoryApp.model.UserPreference
import com.storyApp.edwin.mainStoryApp.model.request.RegisterRequest
import com.storyApp.edwin.mainStoryApp.model.response.AddandRegisterResponse
import com.storyApp.edwin.mainStoryApp.service.RetrofitClient
import com.storyApp.edwin.mainStoryApp.view.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var signupViewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        signupViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SignupViewModel::class.java]
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            setUpRegister()
//            when {
//                name.isEmpty() -> {
//                    binding.nameEditTextLayout.error = "Masukkan email"
//                }
//                email.isEmpty() -> {
//                    binding.emailEditTextLayout.error = "Masukkan email"
//                }
//                password.isEmpty() -> {
//                    binding.passwordEditTextLayout.error = "Masukkan password"
//                }
//                else -> {
//                    signupViewModel.saveUser(UserModel(name, email, password, false))
//                    AlertDialog.Builder(this).apply {
//                        setTitle("Yeah!")
//                        setMessage("Akunnya sudah jadi nih. Yuk, login dan belajar coding.")
//                        setPositiveButton("Lanjut") { _, _ ->
//                            finish()
//                        }
//                        create()
//                        show()
//                    }
//                }
//            }
        }
    }

    private fun setUpRegister() {
        val request = RegisterRequest()
        request.name = binding.nameEditText.text.toString().trim()
        request.email = binding.emailEditText.text.toString().trim()
        request.password = binding.passwordEditText.text.toString().trim()

        val client = RetrofitClient.loginVal().register(request)
        client.enqueue(object: Callback<AddandRegisterResponse> {
            override fun onResponse(
                call: Call<AddandRegisterResponse>,
                response: Response<AddandRegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error!!) {
                        if (responseBody?.error != true) {
                            signupViewModel.saveUser(UserModel(request?.name.toString(), request?.email.toString(), request?.password.toString(), false))
                            alertSignUpSucces()
                        }
                    } else {
                        Toast.makeText(this@SignupActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<AddandRegisterResponse>, t: Throwable) {

            }
        })
    }

    private fun alertSignUpSucces() {
        AlertDialog.Builder(this).apply {
                        setTitle("Selamat")
                        setMessage("Akun anda berhasil terdaftar, Silakan Login")
                        setPositiveButton("Lanjut") { _, _ ->
                            finish()
                        }
                        create()
                        show()
                    }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val nameTextView = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(500)
        val nameEditTextLayout = ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val emailTextView = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500)
        val emailEditTextLayout = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val passwordTextView = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500)
        val passwordEditTextLayout = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)


        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup
            )
            startDelay = 500
        }.start()
    }
}