package com.asct.flickrdemo.data

import com.asct.flickrdemo.domain.PhotoDetail

interface PhotoRepository {
    suspend fun getPhotosByTag(tags: String, page: Int, perPage: Int): List<PhotoDetail>
    suspend fun getPhotoDetail(photoId: String): PhotoDetail
}