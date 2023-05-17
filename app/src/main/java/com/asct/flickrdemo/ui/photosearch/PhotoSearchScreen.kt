package com.asct.flickrdemo.ui.photosearch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val keyword by viewModel.keyword.collectAsState()
    val photosCount = uiState.photos.size
    val error = uiState.error

    Scaffold {
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
                    viewModel.updateKeyword(it)
                })


            if (error != null && uiState.keyword.isNotEmpty()) {
                ErrorSearch(error)
            } else if (photosCount <= 0) {
                EmptySearch()
            } else {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(photosCount) { idx ->
                        uiState.photos.getOrNull(idx)?.let {
                            PhotoItem(it, onPhotoClicked)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorSearch(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Icons.Rounded.Warning,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colors.primary,
            contentDescription = null,
        )
        Text(
            text = "Oops! ",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}

@Composable
private fun EmptySearch() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Icons.Rounded.Search,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colors.primary,
            contentDescription = null,
        )
        Text(
            text = "What are\nyou searching for?",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Search for your favorite thing and find awesome photos",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

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
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(5.dp),
            contentScale = ContentScale.Crop,
        )
    }
}