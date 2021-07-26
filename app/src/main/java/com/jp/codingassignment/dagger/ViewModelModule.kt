package com.jp.codingassignment.dagger

import androidx.lifecycle.ViewModel
import com.jp.codingassignment.ui.album.listing.AlbumListingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListingViewModel::class)
    internal abstract fun bindAlbumListingViewModel(viewModel: AlbumListingViewModel): ViewModel

}