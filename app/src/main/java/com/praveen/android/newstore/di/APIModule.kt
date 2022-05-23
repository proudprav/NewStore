package com.praveen.android.newstore.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.praveen.android.newstore.repository.MockInterceptor
import com.praveen.android.newstore.repository.RetrofitRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class APIModule constructor(private val baseURL: String) {

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL).addConverterFactory(gsonConverterFactory).client(okHttpClient).build()

    @Provides
    fun provideRetroRepository() = RetrofitRepository()

    @Provides
    @Singleton
    fun provideMockInterceptor(): Interceptor = MockInterceptor()

    @Singleton
    @Provides
    fun provideOKHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

}