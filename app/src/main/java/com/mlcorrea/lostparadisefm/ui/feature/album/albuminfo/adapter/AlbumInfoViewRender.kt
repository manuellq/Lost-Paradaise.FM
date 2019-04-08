package com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.adapter

import android.view.ViewGroup
import com.mlcorrea.domain.model.Track
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer

/**
 * Created by manuel.correa on 08/04/19
 */
class AlbumInfoViewRender :
    ViewRenderer<Track, AlbumInfoViewModel>(Track::class.java) {

    override fun bindView(model: Track, holder: AlbumInfoViewModel) {

        holder.apply {
            uiText.text = model.name
        }
    }

    override fun createViewHolder(parent: ViewGroup): AlbumInfoViewModel {
        return AlbumInfoViewModel(
            inflate(R.layout.layout_item_track_row, parent)
        )
    }

}