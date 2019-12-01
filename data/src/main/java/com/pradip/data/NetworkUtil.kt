package com.pradip.data

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.annotation.RequiresPermission
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkUtil @Inject constructor(@ApplicationContext val context: Context) {

    fun isConnected(): Boolean {
        val info = getActiveNetworkInfo(context)
        return info?.isConnected ?: false
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetworkInfo
    }

}