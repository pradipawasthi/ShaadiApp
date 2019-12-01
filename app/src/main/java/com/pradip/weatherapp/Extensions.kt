package com.pradip.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */


operator fun CompositeDisposable.plus(disposable: Disposable) {
    this.add(disposable)
}

@Suppress("DEPRECATION")
fun isInternetConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting ?: false
}