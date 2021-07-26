package com.jp.codingassignment.dagger

import com.jp.codingassignment.ui.album.listing.AlbumListingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [FragmentBuildersModule::class, ViewModelModule::class]
    )
    abstract fun contributeAlbumListingActivity(): AlbumListingActivity
}