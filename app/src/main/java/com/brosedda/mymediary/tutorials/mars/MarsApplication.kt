package com.brosedda.mymediary.tutorials.mars

import android.app.Application
import com.brosedda.mymediary.tutorials.mars.data.AppContainer
import com.brosedda.mymediary.tutorials.mars.data.DefaultAppContainer

class MarsApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}