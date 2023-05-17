package com.asct.flickrdemo.domain

import java.text.SimpleDateFormat
import java.util.Date

data class PhotoDetail(
    val id: String,
    private val mediumUrl: String? = null,
    private val largeUrl: String? = null,
    val title: String? = null,
    val description: String? = null,
    private val datePosted: Date? = null,
    private val dateTaken: Date? = null,
) {
    val photoUrl: String get() = largeUrl ?: mediumUrl.orEmpty()
    val datePostedString = datePosted?.format()
    val dateTakenString = dateTaken?.format()

    private fun Date.format(format: String = "MM/dd/yyyy"): String? {
        val dateFormat = SimpleDateFormat(format)
        return try {
            dateFormat.format(this)
        } catch (e: Exception) {
            null
        }
    }
}