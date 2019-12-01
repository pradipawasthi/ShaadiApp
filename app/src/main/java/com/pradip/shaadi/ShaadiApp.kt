package com.pradip.shaadi

import com.pradip.shaadi.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

class ShaadiApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }


}