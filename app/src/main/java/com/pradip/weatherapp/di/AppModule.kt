package com.pradip.weatherapp.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.pradip.data.ApplicationContext
import com.pradip.weatherapp.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */


@Module
object AppModule {


    @Provides
    @Singleton
    @JvmStatic
    fun provideRetroFit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Provides
    @JvmStatic
    fun provideCompositeDisposable() = CompositeDisposable()


    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    @JvmStatic
    @ApplicationContext
    fun provideContext(application: Application): Context = application.applicationContext


    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor()
                            .also { it.level = HttpLoggingInterceptor.Level.BODY }
                    )
                }
            }
            .build()

}