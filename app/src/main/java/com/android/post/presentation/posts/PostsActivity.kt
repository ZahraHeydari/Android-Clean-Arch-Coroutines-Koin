package com.android.post.presentation.posts

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.post.R
import com.android.post.databinding.ActivityPostsBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PostsActivity : DaggerAppCompatActivity(), OnPostsActivityCallback {

    private val TAG = PostsActivity::class.java.name
    private lateinit var activityPostsBinding: ActivityPostsBinding
    private var mAdapter: PostsAdapter? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        mAdapter =  PostsAdapter()
        activityPostsBinding.postsRecyclerView.adapter = mAdapter

        viewModel.getPosts()

        viewModel.postsData.observe(this, Observer {
            if (it.isNullOrEmpty()) mAdapter?.clearData()
            else mAdapter?.updateData(it)
        })
    }
}
