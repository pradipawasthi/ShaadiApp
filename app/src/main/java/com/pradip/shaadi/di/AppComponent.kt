package com.pradip.shaadi.di

import android.app.Application
import com.pradip.data.db.ShaadiDatabase
import com.pradip.shaadi.ShaadiApp
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
interface AppComponent : AndroidInjector<ShaadiApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}