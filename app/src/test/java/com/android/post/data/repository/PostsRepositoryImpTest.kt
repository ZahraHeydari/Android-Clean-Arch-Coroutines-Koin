package com.android.post.data.repository

import com.android.post.data.model.Post
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsRepositoryImpTest {

    @Mock
    lateinit var postsRepository: PostsRepositoryImp

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this) //for initialization
        postsRepository = Mockito.mock(PostsRepositoryImp::class.java)
    }

    @Test
    fun getPostsData() = runBlocking {
        val posts = mock<List<Post>>()
        Mockito.`when`(postsRepository.getPosts()).thenReturn(posts)

        val result = postsRepository.getPosts()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$posts] must be matches on each other!",
            result,
            CoreMatchers.`is`(posts)
        )
    }
}