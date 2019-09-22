package com.android.post.presentation.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.post.data.model.ErrorModel
import com.android.post.data.model.Post
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.domain.usecase.base.UseCaseResponse
import io.reactivex.disposables.CompositeDisposable

class PostsViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    private val TAG = PostsViewModel::class.java.name
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val postsData = MutableLiveData<List<Post>>()

    fun getPosts() {
        getPostsUseCase.execute(compositeDisposable, null, object : UseCaseResponse<List<Post>> {
            override fun onSuccess(value: List<Post>) {
                Log.i(TAG, "getPostsUseCase: $value")
                postsData.value = value
            }

            override fun onError(error: Throwable, errorModel: ErrorModel?) {
                error.printStackTrace()
            }

        })
    }

}