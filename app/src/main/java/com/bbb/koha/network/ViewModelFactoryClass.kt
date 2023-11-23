package com.bbb.koha.network

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bbb.koha.dashboard.MainViewModel
import com.bbb.koha.login.LoginViewModel
import com.bbb.koha.module.registration.RegistrationViewModel
import com.bbb.koha.module.splash_screen.SplashViewModel

class ViewModelFactoryClass(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(app) as T
        }
        else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(app) as T
        }
        else if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(app) as T
        }
        else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}