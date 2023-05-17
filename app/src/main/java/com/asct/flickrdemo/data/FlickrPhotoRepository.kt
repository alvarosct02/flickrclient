package com.asct.flickrdemo.data

import com.asct.flickrdemo.domain.PhotoDetail
import com.googlecode.flickrjandroid.photos.PhotosInterface
import com.googlecode.flickrjandroid.photos.SearchParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrPhotoRepository(
    private val photosInterface: PhotosInterface
) : PhotoRepository {

    override suspend fun getPhotosByTag(tags: String, page: Int, perPage: Int): List<PhotoDetail> {
        return withContext(Dispatchers.IO) {
            val params = SearchParameters().also {
                it.tags = arrayOf(tags)
            }
            val response = photosInterface.search(params, perPage, page)
            return@withContext response.map {
                PhotoDetail(
                    id = it.id,
                    mediumUrl = it.mediumUrl
                )
            }
        }
    }

    override suspend fun getPhotoDetail(photoId: String): PhotoDetail {
        return withContext(Dispatchers.IO) {
            val response = photosInterface.getPhoto(photoId)
            return@withContext PhotoDetail(
                id = response.id,
                largeUrl = response.largeUrl,
                title = response.title,
                description = response.description,
                datePosted = response.datePosted,
                dateTaken = response.dateTaken,
            )
        }
    }
}