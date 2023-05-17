package com.asct.flickrdemo.ui.photosearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asct.flickrdemo.data.PhotoRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class PhotoSearchViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoSearchUiState())
    val uiState: StateFlow<PhotoSearchUiState> = _uiState.asStateFlow()

    private val _keyword = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    init {
        viewModelScope.launch {
            _keyword
                .debounce(SEARCH_DELAY_MS)
                .distinctUntilChanged()
                .collect {
                    searchPhotos(it)
                }
        }
    }

    private fun searchPhotos(keyword: String) = viewModelScope.launch {
        try {
            val response = photoRepository.getPhotosByTag(
                tags = keyword, page = PAGE, perPage = PHOTOS_PER_PAGE
            )
            _uiState.update { state ->
                state.copy(keyword = keyword, photos = response, error = null)
            }
        } catch (e: Exception) {
            _uiState.update { state ->
                state.copy(
                    keyword = keyword,
                    photos = emptyList(),
                    error = "We couldn't get the photos.\nPlease try again"
                )
            }
        }
    }

    fun updateKeyword(keyword: String) {
        _keyword.value = keyword
    }

    companion object {
        const val SEARCH_DELAY_MS = 300L
        const val PHOTOS_PER_PAGE = 50
        const val PAGE = 1
    }
}