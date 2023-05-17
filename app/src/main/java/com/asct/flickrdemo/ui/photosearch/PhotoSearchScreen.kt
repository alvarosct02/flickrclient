package com.asct.flickrdemo.ui.photosearch

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.asct.flickrdemo.data.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun PhotoSearchScreen(
    photoRepository: PhotoRepository = get()
) {
    LaunchedEffect(Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = photoRepository.getPhotosByTag(
                    tags = "ocean",
                    page = 1,
                    perPage = 50
                )
                response.forEach {
                    Log.e("ASCT", it.toString())
                }
            } catch (e: Exception) {
                Log.e("ASCT", e.localizedMessage)
                e.printStackTrace()
            }
        }
    }
}