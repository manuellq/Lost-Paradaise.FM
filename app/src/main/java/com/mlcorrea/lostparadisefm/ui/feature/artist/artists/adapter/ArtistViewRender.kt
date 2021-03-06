package com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer
import com.mlcorrea.lostparadisefm.ui.utils.getUrlImage
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by manuel on 07/04/19
 */
class ArtistViewRender(private val clickListener: (View, Artist) -> Unit = { _: View, _: Artist -> }) :
    ViewRenderer<Artist, ArtistViewModel>(Artist::class.java) {

    @SuppressLint("CheckResult")
    override fun bindView(model: Artist, holder: ArtistViewModel) {

        holder.apply {
            val context = itemView.context
            uiName.text = model.name

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

        RxView.clicks(holder.itemView)
            .throttleFirst(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                clickListener(holder.itemView, model)
            }
    }

    override fun createViewHolder(parent: ViewGroup): ArtistViewModel {
        return ArtistViewModel(
            inflate(R.layout.layout_item_album, parent)
        )
    }

}