package com.asct.flickrdemo.ui.photosearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asct.flickrdemo.data.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoSearchViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoSearchUiState())
    val uiState: StateFlow<PhotoSearchUiState> = _uiState.asStateFlow()

    fun searchPhotos(keyword: String) = viewModelScope.launch {
        try {
            val response = photoRepository.getPhotosByTag(
                tags = keyword, page = PAGE, perPage = PHOTOS_PER_PAGE
            )
            _uiState.update { state ->
                state.copy(photos = response)
            }
        } catch (e: Exception) {
            _uiState.update { state ->
                state.copy(error = "We couldn't get the photos. Please try again")
            }
        }
    }

    companion object {
        const val PHOTOS_PER_PAGE = 50
        const val PAGE = 1
    }
}