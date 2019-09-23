package com.android.post.domain.usecase.base

import com.android.post.data.source.remote.ApiErrorHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params>(apiErrorHandle: ApiErrorHandle?) where Type : Any {

    abstract suspend fun run(params: Params): Type

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params?,
        onResult: (Type) -> Unit = {}
    ) {
        val backgroundJob = scope.async { params?.let { run(it) } }
        scope.launch { backgroundJob.await()?.let { onResult(it) } }
    }
}