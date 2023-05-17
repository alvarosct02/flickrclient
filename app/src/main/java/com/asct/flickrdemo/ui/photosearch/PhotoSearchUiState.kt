package com.asct.flickrdemo.ui.photosearch

import com.asct.flickrdemo.domain.PhotoDetail

data class PhotoSearchUiState(
    val keyword: String = "",
    val photos: List<PhotoDetail> = emptyList(),
    val error: String? = null,
)