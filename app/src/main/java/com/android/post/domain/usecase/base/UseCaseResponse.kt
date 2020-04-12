package com.android.post.domain.usecase.base

import com.android.post.domain.model.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

