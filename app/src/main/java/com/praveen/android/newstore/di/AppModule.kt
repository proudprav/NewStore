package com.praveen.android.newstore.di

import android.content.Context
import com.praveen.android.newstore.application.NewStoreApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(private var myApplication: NewStoreApplication) {

    @Provides
    fun provideMyRetroApplication(): NewStoreApplication {
        return myApplication
    }

    @Provides
    fun provideContext(): Context? {
        return myApplication.applicationContext
    }

}