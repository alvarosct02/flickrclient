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
import com.asct.flickrdemo.ui.theme.FlickrDemoTheme
import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.SearchParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
}

fun testFlickr(keyword: String) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val client = Flickr(BuildConfig.FLICKR_API_KEY, BuildConfig.FLICKR_API_SECRET)

            val response = client.photosInterface.search(SearchParameters().also {
                it.text = keyword
            }, 50, 1)
            response.forEach {
                Log.e("ASCT", it.thumbnailUrl)
            }
        } catch (e: Exception) {
            Log.e("ASCT", e.localizedMessage)
            e.printStackTrace()
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