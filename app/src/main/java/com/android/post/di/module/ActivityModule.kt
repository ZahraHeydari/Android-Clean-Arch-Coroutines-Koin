package com.android.post.di.module

import com.android.post.presentation.posts.PostsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {


    @ContributesAndroidInjector
    fun postsActivityInjector(): PostsActivity
}
