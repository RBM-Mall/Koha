package com.bbb.koha.app

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private lateinit var application: Application
        fun getInstance(): Application {
            return application
        }
    }
}