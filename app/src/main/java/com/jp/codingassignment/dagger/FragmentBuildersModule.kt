package com.jp.codingassignment.dagger

import com.jp.codingassignment.ui.album.listing.AlbumListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeAlbumListingFragment(): AlbumListingFragment
}