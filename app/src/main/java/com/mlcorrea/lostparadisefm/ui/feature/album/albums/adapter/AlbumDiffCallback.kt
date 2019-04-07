package com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
class AlbumDiffCallback : DiffUtil.ItemCallback<ViewModelData>() {

    override fun areItemsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
        return if (oldItem is Album && newItem is Album) {
            oldItem.artist == newItem.artist && oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
        return if (oldItem is Album && newItem is Album) {
            oldItem.artist == newItem.artist && oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }
    }
}