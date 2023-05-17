package com.asct.flickrdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.asct.flickrdemo.data.PhotoRepository
import com.asct.flickrdemo.ui.theme.FlickrDemoTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val photoRepository: PhotoRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }

        testFlickr("ocean")
    }

    private fun testFlickr(keyword: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = photoRepository.getPhotosByTag(
                    tags = keyword,
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlickrDemoTheme {
        Greeting("Android")
    }
}