package com.example.mystore.application

import android.app.Application
import android.content.Context

class AppApplication : Application() {

    companion object {
        private lateinit var instance: AppApplication

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }
}