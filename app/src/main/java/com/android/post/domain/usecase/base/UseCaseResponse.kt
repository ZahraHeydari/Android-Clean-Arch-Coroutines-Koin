package com.android.post.domain.usecase.base

import com.android.post.data.model.ErrorModel

interface UseCaseResponse<T> {

    fun onSuccess(value: T)

    fun onError(error: Throwable, errorModel: ErrorModel?)
}
