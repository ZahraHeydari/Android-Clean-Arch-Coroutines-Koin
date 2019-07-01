package com.android.post.data.source.remote

import com.android.post.data.model.Post
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    fun getPosts(): Single<List<Post>>
}