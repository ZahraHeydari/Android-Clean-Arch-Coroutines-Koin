package com.android.post.domain.usecase

import com.android.post.data.model.Post
import com.android.post.data.source.remote.ApiErrorHandle
import com.android.post.domain.repository.PostsRepository
import com.android.post.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class GetPostsUseCase constructor(private val postsRepository: PostsRepository, apiErrorHandle: ApiErrorHandle?)
    :SingleUseCase<Any,List<Post>>(apiErrorHandle){
    override fun execute(input: Any?): Single<List<Post>> {
        return postsRepository.getPosts()
    }
}