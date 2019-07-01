package com.android.post.domain.repository

import com.android.post.data.model.Post
import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Single<List<Post>>
}