package com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.adapter

import android.view.ViewGroup
import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer
import com.mlcorrea.lostparadisefm.ui.utils.getUrlImage
import com.squareup.picasso.Picasso

/**
 * Created by manuel.correa on 08/04/19
 */
class ArtistInfoViewRender : ViewRenderer<Artist, ArtistInfoViewModel>(Artist::class.java) {

    override fun bindView(model: Artist, holder: ArtistInfoViewModel) {

        holder.apply {
            val context = itemView.context
            uiText.text = model.name
            val image = model.images.getUrlImage(TypeImage.MEDIUM)
            if (image != null && image.isNotEmpty()) {
                Picasso.with(context)
                    .load(image)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
                    .into(uiImage)
            } else {
                Picasso.with(context)
                    .load(R.drawable.ic_audiotrack)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
                    .into(uiImage)
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): ArtistInfoViewModel {
        return ArtistInfoViewModel(
            inflate(R.layout.layout_item_artist_row, parent)
        )
    }

}