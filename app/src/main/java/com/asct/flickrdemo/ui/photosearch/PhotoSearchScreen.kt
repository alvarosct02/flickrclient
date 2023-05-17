package com.asct.flickrdemo.ui.photosearch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.asct.flickrdemo.domain.PhotoDetail
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoSearchScreen(
    onPhotoClicked: (String) -> Unit,
    viewModel: PhotoSearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var keyword by remember { mutableStateOf("") }
    val photosCount = uiState.photos.size

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = keyword,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            label = {
                Text(text = "Search")
            },
            trailingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(text = "e.g. \"moon\"")
            },
            onValueChange = {
                keyword = it
                viewModel.searchPhotos(it)
            })

        if (photosCount > 0) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 128.dp)
            ) {
                items(photosCount) { idx ->
                    PhotoItem(uiState.photos[idx], onPhotoClicked)
                }
            }
        }
    }
}

@Composable
private fun PhotoItem(
    item: PhotoDetail,
    onPhotoClicked: (String) -> Unit,
) {
    Box(modifier = Modifier.clickable {
        onPhotoClicked(item.id)
    }) {
//        Text(text = item.photoUrl)
        Image(
            painter = rememberAsyncImagePainter(item.photoUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
    }
}