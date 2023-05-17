package com.asct.flickrdemo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.asct.flickrdemo.ui.photodetail.PhotoDetailScreen
import com.asct.flickrdemo.ui.photosearch.PhotoSearchScreen

@Composable
fun FlickrNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "photoSearch") {
        composable("photoSearch") {
            PhotoSearchScreen(
                onPhotoClicked = { navController.navigate("photoDetail/$it") }
            )
        }
        composable(
            "photoDetail/{photoId}",
            arguments = listOf(navArgument("photoId") { type = NavType.StringType })
        ) {
            PhotoDetailScreen(
                photoId = it.arguments?.getString("photoId").orEmpty(),
                onBack = { navController.popBackStack() }
            )
        }
    }
}