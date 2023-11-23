package com.bbb.koha.module.splash_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bbb.koha.R
import com.bbb.koha.app.BaseActivity
import com.bbb.koha.app.MVVMBindingActivity
import com.bbb.koha.common.Constant
import com.bbb.koha.common.SharedPreference
import com.bbb.koha.dashboard.MainViewModel
import com.bbb.koha.databinding.ActivityMainBinding
import com.bbb.koha.databinding.ActivitySplashBinding
import com.bbb.koha.login.LoginActivity
import com.bbb.koha.network.ViewModelFactoryClass
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : MVVMBindingActivity<ActivitySplashBinding>() {
    private lateinit var viewModel: SplashViewModel
    override fun initializeView() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactoryClass(application)
        )[SplashViewModel::class.java]
        viewModel.getToken()
        setObserver()
    }

    override fun provideViewResource(): Int {
        return R.layout.activity_splash
    }

    override fun onClick(p0: View?) {
    }

    private fun setObserver() {
        viewModel.tokenResponse.observe(this, Observer {
            var gson = Gson().toJson(it)
            it?.let { it1 ->
                sharedPreference.save(Constant.ACCESS_TOKEN, it1.accessToken!!)
                sharedPreference.save(Constant.TOKEN_TYPE, it1.tokenType!!)
                sharedPreference.save(Constant.EXPIRES_IN, it1.expiresIn!!)
            }
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                startNewActivity(LoginActivity())
            }
            Log.d("response","$gson")
        })
    }
}