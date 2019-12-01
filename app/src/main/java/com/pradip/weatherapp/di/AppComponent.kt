package com.pradip.weatherapp.di

import android.app.Application
import com.pradip.weatherapp.WeatherApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelProviderModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<WeatherApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}