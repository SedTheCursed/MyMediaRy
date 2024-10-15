package com.brosedda.mymediary

import android.app.Application
import com.brosedda.mymediary.data.AppContainer
import com.brosedda.mymediary.data.DefaultAppContainer

class MyMediaRyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}