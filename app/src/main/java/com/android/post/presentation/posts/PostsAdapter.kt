package com.android.post.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.post.R
import com.android.post.data.model.Post
import com.android.post.databinding.HolderPostBinding

class PostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = PostsAdapter::class.java.simpleName
    private val mPostList: MutableList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_post, parent, false
        )
        return PostViewHolder(holderPostBinding)
    }

    override fun getItemCount(): Int {
        return if(mPostList.isNullOrEmpty()) 0 else mPostList.size
    }

    private fun getItem(position: Int): Post {
        return mPostList[position]
    }


    fun updateData(list: List<Post>) {
        mPostList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        mPostList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(getItem(position))
    }

    inner class PostViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(post: Post) {
            (viewDataBinding as HolderPostBinding).post = post
        }
    }
}