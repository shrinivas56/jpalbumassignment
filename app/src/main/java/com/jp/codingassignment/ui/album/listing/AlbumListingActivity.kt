package com.jp.codingassignment.ui.album.listing

import android.os.Bundle
import com.jp.codingassignment.R
import com.jp.codingassignment.base.BaseActivity
import com.jp.codingassignment.databinding.ActivityAlbumListingBinding

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
class AlbumListingActivity  : BaseActivity<ActivityAlbumListingBinding, AlbumListingViewModel>(){


        private val TAG: String = javaClass.simpleName

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (savedInstanceState == null){
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fmContainer, AlbumListingFragment.newInstance("Oi"))
                    .commit()
            }
        }

        override fun getLayoutId(): Int = R.layout.activity_album_listing
        override fun getModel(): Class<AlbumListingViewModel> = AlbumListingViewModel::class.java
}