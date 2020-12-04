package com.android.post.domain.usecase

import com.android.post.domain.model.Post
import com.android.post.domain.repository.PostsRepository
import com.android.post.domain.usecase.base.UseCase

class GetPostsUseCase constructor(
    private val postsRepository: PostsRepository
) : UseCase<List<Post>, Any?>() {

    override suspend fun run(params: Any?): List<Post> {
        return postsRepository.getPosts()
    }

}