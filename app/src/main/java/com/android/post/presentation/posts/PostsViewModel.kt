package com.android.post.presentation.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.post.domain.model.ApiError
import com.android.post.domain.model.Post
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.domain.usecase.base.UseCaseResponse
import kotlinx.coroutines.cancel


class PostsViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    val postsData = MutableLiveData<List<Post>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getPosts() {
        showProgressbar.value = true
        getPostsUseCase.invoke(viewModelScope, null, object : UseCaseResponse<List<Post>> {
                override fun onSuccess(result: List<Post>) {
                    Log.i(TAG, "result: $result")
                    postsData.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }
            },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = PostsViewModel::class.java.name
    }

}