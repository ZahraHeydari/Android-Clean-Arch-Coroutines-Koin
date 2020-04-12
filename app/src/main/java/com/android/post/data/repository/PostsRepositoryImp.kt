package com.android.post.data.repository

import com.android.post.domain.model.Post
import com.android.post.data.source.remote.ApiService
import com.android.post.domain.repository.PostsRepository

class PostsRepositoryImp(private val apiService: ApiService) : PostsRepository {

    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}