package com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.renders.diffutils.SortedListComparatorWrapper
import java.util.*

/**
 * Created by manuel.correa on 08/04/19
 */
class ArtistInfoComparator(adapter: RecyclerView.Adapter<*>) :
    SortedListComparatorWrapper<ViewModelData>(
        adapter,
        DEFAULT_ORDER
    ) {

    override fun areItemsTheSame(oldItem: ViewModelData?, newItem: ViewModelData?): Boolean {
        return if (oldItem is Artist && newItem is Artist) {
            oldItem.name == newItem.name
        } else {
            throw IllegalArgumentException("Model must be Artist")
        }
    }

    override fun areContentsTheSame(oldItem: ViewModelData?, newItem: ViewModelData?): Boolean {
        if (oldItem is Artist && newItem is Artist) {
            return oldItem.name == newItem.name
        }
        return oldItem == newItem
    }

    companion object {
        private val DEFAULT_ORDER = ArtistOrderById()
    }

    class ArtistOrderById : Comparator<ViewModelData> {
        override fun compare(oldItem: ViewModelData, newItem: ViewModelData): Int {
            return if (oldItem is Artist && newItem is Artist) {
                oldItem.name.compareTo(newItem.name)
            } else {
                throw IllegalArgumentException("Model must be Artist")
            }
        }
    }
}