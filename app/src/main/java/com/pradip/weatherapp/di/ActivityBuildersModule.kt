package com.pradip.weatherapp.di

import androidx.lifecycle.ViewModel
import com.doubtnutapp.di.ViewModelKey
import com.doubtnutapp.di.scope.PerActivity
import com.pradip.data.repository.WeatherForecastRepositoryImpl
import com.pradip.data.service.WeatherService
import com.pradip.domain.repository.WeatherForecastRepository
import com.pradip.weatherapp.ui.MainActivity
import com.pradip.weatherapp.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindWeatherForecastRepositoryImpl(weatherForecastRepositoryImpl: WeatherForecastRepositoryImpl): WeatherForecastRepository

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideWeatherService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)

    }
}