package com.android.post.data.source.remote

import com.android.post.data.model.Post
import com.android.post.domain.usecase.base.UseCaseResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}