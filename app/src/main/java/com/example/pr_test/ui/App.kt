package com.example.pr_test.ui

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        private lateinit var appContext: Context
        fun getContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        appContext = this.applicationContext
        super.onCreate()
    }
}