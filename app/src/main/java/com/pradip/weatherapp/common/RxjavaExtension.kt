package com.pradip.weatherapp.common

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */


fun <T> Single<T>.applyIoToMainSchedulerOnSingle(): Single<T> = this.compose {
    it.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
}