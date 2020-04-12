package com.android.post.presentation.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.post.domain.model.ErrorModel
import com.android.post.domain.model.Post
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.domain.usecase.base.UseCaseResponse
import com.android.post.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PostsViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : BaseViewModel() {

    val postsData = MutableLiveData<List<Post>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun getPosts() {
        showProgressbar.value = true
        getPostsUseCase.invoke(null, object : UseCaseResponse<List<Post>> {
            override fun onSuccess(result: List<Post>) {
                Log.i(TAG, "result: $result")
                postsData.value = result
                showProgressbar.value = false
            }

            override fun onError(errorModel: ErrorModel?) {
                messageData.value = errorModel?.message
                showProgressbar.value = false
            }
        })
    }

    companion object {
        private val TAG = PostsViewModel::class.java.name
    }

}