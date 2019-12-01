package com.pradip.data.mapper

interface BaseMapper<in Src, out Des> {
    fun map(srcObject: Src): Des
}