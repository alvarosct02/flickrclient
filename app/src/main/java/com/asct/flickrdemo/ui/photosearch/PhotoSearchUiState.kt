package com.asct.flickrdemo.ui.photosearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asct.flickrdemo.data.PhotoRepository
import com.asct.flickrdemo.domain.PhotoDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PhotoSearchUiState(
    val photos: List<PhotoDetail> = emptyList(),
    private val error: String? = null,
)