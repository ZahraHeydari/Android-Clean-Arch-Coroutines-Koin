package com.android.post.domain.usecase.base

import com.android.post.data.source.remote.ApiErrorHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

abstract class UseCase<Type, in Params>(private val apiErrorHandle: ApiErrorHandle?) where Type : Any {

    abstract suspend fun run(params: Params? = null): Type

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params?,
        onResult: (UseCaseResponse<Type>)
    ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch {
            backgroundJob.await().let {
                try {
                    onResult.onSuccess(it)
                } catch (e: HttpException) {
                    onResult.onError(apiErrorHandle?.traceErrorException(e))
                }
            }
        }
    }
}