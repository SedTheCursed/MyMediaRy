package com.brosedda.mymediary.tutorials.inventory

import android.app.Application
import com.brosedda.mymediary.tutorials.inventory.data.AppContainer
import com.brosedda.mymediary.tutorials.inventory.data.AppDataContainer

class InventoryApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}