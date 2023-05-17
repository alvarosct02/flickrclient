package com.asct.flickrdemo.di

import com.asct.flickrdemo.BuildConfig
import com.asct.flickrdemo.data.FlickrPhotoRepository
import com.asct.flickrdemo.data.PhotoRepository
import com.asct.flickrdemo.ui.photosearch.PhotoSearchViewModel
import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.PhotosInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotosInterface> {
        Flickr(
            BuildConfig.FLICKR_API_KEY,
            BuildConfig.FLICKR_API_SECRET
        ).photosInterface
    }
    single<PhotoRepository> { FlickrPhotoRepository(get()) }
}

val viewModelModule = module {
    viewModel { PhotoSearchViewModel(get()) }
}