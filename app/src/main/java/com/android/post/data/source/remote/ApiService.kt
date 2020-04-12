package com.android.post.data.source.remote

import com.android.post.domain.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}