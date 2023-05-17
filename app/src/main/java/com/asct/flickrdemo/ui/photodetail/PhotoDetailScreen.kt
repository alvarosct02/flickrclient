package com.asct.flickrdemo.ui.photodetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoDetailScreen(
    photoId: String,
    onBack: () -> Unit,
    viewModel: PhotoDetailViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getDetail(photoId)
    }

    val uiState by viewModel.uiState.collectAsState()
    val detail = uiState.detail ?: return

    Column(modifier = Modifier.fillMaxWidth()) {


        Text(text = detail.title.orEmpty())
        Text(text = detail.description.orEmpty())
        Image(
            painter = rememberAsyncImagePainter(detail.photoUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = detail.dateTaken.toString())
        Text(text = detail.datePosted.toString())

    }
}