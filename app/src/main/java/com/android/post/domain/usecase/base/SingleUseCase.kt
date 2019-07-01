package com.android.post.domain.usecase.base

import com.android.post.data.source.remote.ApiErrorHandle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<R, T>(private val apiErrorHandle: ApiErrorHandle) : UseCase<R, Single<T>>() {

    fun execute(
        compositeDisposable: CompositeDisposable,
        input: R?,
        onResponse: UseCaseResponse<T>
    ): Disposable {
        return this.execute(input)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse.onSuccess(it)
            }, {
                onResponse.onError(it, apiErrorHandle.traceErrorException(it))
            }).also {
                compositeDisposable.add(it)
            }
    }

}