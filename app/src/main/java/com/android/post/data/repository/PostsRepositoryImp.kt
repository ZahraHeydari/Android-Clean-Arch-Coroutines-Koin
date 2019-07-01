package com.android.post.data.repository

import com.android.post.data.model.Post
import com.android.post.data.source.remote.ApiService
import com.android.post.domain.repository.PostsRepository
import io.reactivex.Single

class PostsRepositoryImp(private val apiService: ApiService) : PostsRepository {

    override fun getPosts(): Single<List<Post>> {
        return apiService.getPosts()
    }
}