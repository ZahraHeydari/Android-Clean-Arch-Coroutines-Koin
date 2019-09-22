package com.android.post.di.module

import com.android.post.BuildConfig
import com.android.post.data.repository.PostsRepositoryImp
import com.android.post.data.source.remote.ApiErrorHandle
import com.android.post.data.source.remote.ApiService
import com.android.post.domain.repository.PostsRepository
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.presentation.posts.PostsViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val PostModule = module {


    // PostsViewModel ViewModel
    viewModel { PostsViewModel(get()) }

    single { createGetPostsUseCase(get(), createApiErrorHandle()) }

    // single instance of PostsRepository
    //single<PostsRepository> { PostsRepositoryImp(get()) }
    single { createPostRepository(get()) }

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { createGsonConverterFactory() }

    single { createGson() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String):Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}


fun createGson(): Gson {
    return GsonBuilder().create()
}

fun createGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}


fun createApiErrorHandle(): ApiErrorHandle {
    return ApiErrorHandle()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createPostRepository(apiService: ApiService): PostsRepository {
    return PostsRepositoryImp(apiService)
}

fun createGetPostsUseCase(
    postsRepository: PostsRepository,
    apiErrorHandle: ApiErrorHandle
): GetPostsUseCase {
    return GetPostsUseCase(postsRepository, apiErrorHandle)
}
