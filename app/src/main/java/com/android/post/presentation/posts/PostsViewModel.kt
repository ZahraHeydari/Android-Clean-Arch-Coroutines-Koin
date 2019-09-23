package com.android.post.presentation.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.post.data.model.ErrorModel
import com.android.post.data.model.Post
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.domain.usecase.base.UseCaseResponse
import com.android.post.presentation.base.BaseViewModel

class PostsViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : BaseViewModel() {

    private val TAG = PostsViewModel::class.java.name
    val postsData = MutableLiveData<List<Post>>()
    var messageData = MutableLiveData<String>()

    fun getPosts() {
        getPostsUseCase.invoke(scope, null, object : UseCaseResponse<List<Post>> {
            override fun onSuccess(result: List<Post>) {
                Log.i(TAG,"result: $result")
                postsData.value = result
            }

            override fun onError(errorModel: ErrorModel?) {
                messageData.value = errorModel?.message
            }
        })
    }

}