package com.jp.codingassignment.ui.album.listing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jp.codingassignment.R
import com.jp.codingassignment.base.BaseFragment
import com.jp.codingassignment.databinding.FragmentAlbumListingBinding

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
class AlbumListingFragment : BaseFragment<FragmentAlbumListingBinding, AlbumListingViewModel>(){

    private var albumListingAdapter = AlbumListingAdapter()

    private val TAG: String = javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecyclerView()
    }

    private fun initView() {
        binding!!.viewModel = viewModel
        viewModel.albumsLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.isLoading.set(View.GONE)
            if (it != null && it.isNotEmpty()) {
                viewModel.isDataAvailable.set(View.GONE)
                albumListingAdapter.updateItems(it)
            } else {
                viewModel.isDataAvailable.set(View.VISIBLE)
            }
        })
        viewModel.onNetworkError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
        viewModel.getData()
    }

    private fun initRecyclerView() {

        binding!!.rvAlbum.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            // specify an adapter (see also next example)
            viewModel!!.albumsLiveData.value
            adapter = albumListingAdapter
        }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"

        fun newInstance(param1: String): AlbumListingFragment {
            val fragment = AlbumListingFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int = R.layout.fragment_album_listing
    override fun getModel(): Class<AlbumListingViewModel> = AlbumListingViewModel::class.java
    /*override fun noNetworkError(message: String) {
    }*/
}