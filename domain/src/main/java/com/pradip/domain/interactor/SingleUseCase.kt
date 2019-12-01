package com.pradip.domain.interactor

import io.reactivex.Single

interface SingleUseCase<T, PARAMS> {
    fun execute(param: PARAMS): Single<T>
}