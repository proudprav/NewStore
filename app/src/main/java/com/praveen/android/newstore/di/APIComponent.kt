package com.praveen.android.newstore.di

import com.praveen.android.newstore.repository.RetrofitRepository
import com.praveen.android.newstore.utils.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, APIModule::class])
interface APIComponent {

    fun inject(retrofitRepository: RetrofitRepository)

    fun inject(retrofitRepository: ViewModelFactory)

}