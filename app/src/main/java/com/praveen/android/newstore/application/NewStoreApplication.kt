package com.praveen.android.newstore.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.praveen.android.newstore.di.APIComponent
import com.praveen.android.newstore.di.APIModule
import com.praveen.android.newstore.di.DaggerAPIComponent
import com.praveen.android.newstore.repository.ApiURL


class NewStoreApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var ctx: Context
        lateinit var apiComponent: APIComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        initDaggerComponent()
    }

    private fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(ApiURL.BASE_URL))
            .build()
        return apiComponent

    }
}