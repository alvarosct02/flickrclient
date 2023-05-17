package com.asct.flickrdemo.ui.photodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asct.flickrdemo.data.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoDetailViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoDetailUiState())
    val uiState: StateFlow<PhotoDetailUiState> = _uiState.asStateFlow()

    fun getDetail(photoId: String) = viewModelScope.launch {
        try {
            val response = photoRepository.getPhotoDetail(photoId)
            _uiState.update { state ->
                state.copy(detail = response)
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