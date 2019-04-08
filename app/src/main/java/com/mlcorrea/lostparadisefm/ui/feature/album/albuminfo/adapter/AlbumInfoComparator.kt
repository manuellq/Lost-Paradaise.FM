package com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mlcorrea.domain.model.Track
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.renders.diffutils.SortedListComparatorWrapper
import java.util.*

/**
 * Created by manuel.correa on 08/04/19
 */
class AlbumInfoComparator(adapter: RecyclerView.Adapter<*>) :
    SortedListComparatorWrapper<ViewModelData>(
        adapter,
        DEFAULT_ORDER
    ) {

    override fun areItemsTheSame(oldItem: ViewModelData?, newItem: ViewModelData?): Boolean {
        return if (oldItem is Track && newItem is Track) {
            oldItem.name == newItem.name
        } else {
            throw IllegalArgumentException("Model must be Album")
        }
    }

    override fun areContentsTheSame(oldItem: ViewModelData?, newItem: ViewModelData?): Boolean {
        if (oldItem is Track && newItem is Track) {
            return oldItem.name == newItem.name
        }
        return oldItem == newItem
    }

    companion object {
        private val DEFAULT_ORDER = AlbumOrderById()
    }

    class AlbumOrderById : Comparator<ViewModelData> {
        override fun compare(oldItem: ViewModelData, newItem: ViewModelData): Int {
            return if (oldItem is Track && newItem is Track) {
                oldItem.rank?.compareTo(newItem.rank ?: 0) ?: 0
            } else {
                throw IllegalArgumentException("Model must be Album")
            }
        }
    }
}