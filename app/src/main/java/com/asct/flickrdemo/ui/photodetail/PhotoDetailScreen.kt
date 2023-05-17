package com.asct.flickrdemo.ui.photodetail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.asct.flickrdemo.data.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun PhotoDetailScreen(
    photoId: String,
    photoRepository: PhotoRepository = get()
) {
    LaunchedEffect(Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = photoRepository.getPhotoDetail(photoId)
                Log.e("ASCT", response.toString())
            } catch (e: Exception) {
                Log.e("ASCT", e.localizedMessage)
                e.printStackTrace()
            }
        }
    }
}