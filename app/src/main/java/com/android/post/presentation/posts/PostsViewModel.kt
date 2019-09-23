package com.android.post.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.post.data.model.Post
import com.android.post.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostsViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel(),
    CoroutineScope {

    private val TAG = PostsViewModel::class.java.name
    val postsData = MutableLiveData<List<Post>>()
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    init {
        launch { getPosts() }
    }

    private suspend fun getPosts() {
        postsData.postValue(getPostsUseCase.run(null))
    }

}