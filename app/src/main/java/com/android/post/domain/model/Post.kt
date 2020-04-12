package com.android.post.domain.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Post(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
)