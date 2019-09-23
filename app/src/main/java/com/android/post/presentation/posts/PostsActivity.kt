package com.android.post.presentation.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.post.R
import com.android.post.databinding.ActivityPostsBinding
import org.koin.android.viewmodel.ext.android.viewModel


class PostsActivity : AppCompatActivity(), OnPostsActivityCallback {

    private val TAG = PostsActivity::class.java.name
    private lateinit var activityPostsBinding: ActivityPostsBinding
    private var mAdapter: PostsAdapter? = null
    val postViewModel: PostsViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        mAdapter = PostsAdapter()
        activityPostsBinding.postsRecyclerView.adapter = mAdapter

        postViewModel.getPosts()

        postViewModel.postsData.observe(this, Observer {
            activityPostsBinding.postsProgressBar.visibility = View.GONE
            if (it.isNullOrEmpty()) mAdapter?.clearData()
            else mAdapter?.updateData(it)
        })

        postViewModel.messageData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}
