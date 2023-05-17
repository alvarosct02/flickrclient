package com.asct.flickrdemo.domain

import java.util.Date

data class PhotoDetail(
    val id: String,
    private val mediumUrl: String? = null,
    private val largeUrl: String? = null,
    val title: String? = null,
    val description: String? = null,
    val datePosted: Date? = null,
    val dateTaken: Date? = null,
) {
    val photoUrl: String get() = largeUrl ?: mediumUrl.orEmpty()
}