package com.mlcorrea.lostparadisefm.ui.feature.track.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mlcorrea.domain.model.Track
import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
class TrackDiffCallback : DiffUtil.ItemCallback<ViewModelData>() {

    override fun areItemsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
        return if (oldItem is Track && newItem is Track) {
            oldItem.listeners == newItem.listeners && oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
        return if (oldItem is Track && newItem is Track) {
            oldItem.listeners == newItem.listeners && oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }
    }
}