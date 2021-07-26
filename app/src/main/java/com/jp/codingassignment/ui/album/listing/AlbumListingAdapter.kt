package com.jp.codingassignment.ui.album.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jp.codingassignment.R
import com.jp.codingassignment.databinding.CustomAlbumListingRowBinding
import com.jp.codingassignment.services.db.entity.Album

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
class AlbumListingAdapter(private var items: List<Album>) : RecyclerView.Adapter<AlbumListingAdapter.AlbumItemViewHolder>() {

    val TAG = javaClass.simpleName

    constructor() : this(emptyList())

    // Provide a reference to the views for each data item
    class AlbumItemViewHolder(var view: CustomAlbumListingRowBinding) : RecyclerView.ViewHolder(view.root)

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder!!.view.album = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val inflater = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: CustomAlbumListingRowBinding = DataBindingUtil.inflate(inflater, R.layout.custom_album_listing_row, parent, false)

        return AlbumItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(albums: List<Album>){
        items = albums
        notifyDataSetChanged()
    }

}