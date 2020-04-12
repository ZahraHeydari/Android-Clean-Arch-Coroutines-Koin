package com.android.post.domain.repository

import com.android.post.domain.model.Post

interface PostsRepository {

    suspend fun getPosts(): List<Post>
}