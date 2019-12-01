package com.pradip.weatherapp.di

import androidx.lifecycle.ViewModelProvider
import com.pradip.weatherapp.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */


@Module
abstract class ViewModelProviderModule {

    @Binds
    internal abstract fun bindViewFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}