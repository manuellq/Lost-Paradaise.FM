package com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
class ArtistDiffCallback : DiffUtil.ItemCallback<ViewModelData>() {

    override fun areItemsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
        return if (oldItem is Artist && newItem is Artist) {
            oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
        return if (oldItem is Artist && newItem is Artist) {
            oldItem.listeners == newItem.listeners && oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }
    }
}