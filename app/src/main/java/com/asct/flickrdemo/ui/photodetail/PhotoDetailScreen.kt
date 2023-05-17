package com.asct.flickrdemo.ui.photodetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.asct.flickrdemo.ui.components.AppBar
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

    Scaffold(
        topBar = { AppBar(onBack = onBack, title = uiState.detail?.title) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = detail.title.orEmpty(),
                style = MaterialTheme.typography.h4,
            )

            detail.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                ChipInfo("Posted", uiState.detail?.datePostedString.orEmpty())
                ChipInfo("Taken", uiState.detail?.dateTakenString.orEmpty())
            }
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(detail.photoUrl)
                    .size(Size.ORIGINAL)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .then(
                        (painter.state as? AsyncImagePainter.State.Success)
                            ?.painter
                            ?.intrinsicSize
                            ?.let { intrinsicSize ->
                                Modifier.aspectRatio(intrinsicSize.width / intrinsicSize.height)
                            } ?: Modifier
                    )
            )
        }
    }
}

@Composable
private fun ChipInfo(name: String, value: String) {
    Row(
        Modifier.border(
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            shape = CircleShape
        )
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape.copy(bottomEnd = CornerSize(0.dp), topEnd = CornerSize(0.dp))
                )
                .padding(6.dp)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(6.dp)
        )
    }
}