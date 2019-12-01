package com.pradip.weatherapp.common

import android.view.View
import java.text.SimpleDateFormat

/**
 *  Created by Pradip Awasthi on 2019-09-15.
 */


fun View.setVisibleState(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


fun getDayNameFromDate(date: String): String {

    val simpleSateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = simpleSateFormat.parse(date)
    val simpleDateformat = SimpleDateFormat("EEEE")
    return simpleDateformat.format(date)
}