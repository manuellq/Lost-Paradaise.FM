package com.mlcorrea.lostparadisefm.ui.feature.track.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Image
import com.mlcorrea.domain.model.Track
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer
import com.mlcorrea.lostparadisefm.ui.utils.getUrlImage
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by manuel on 07/04/19
 */
class TrackViewRender(private val clickListener: (View, Track) -> Unit = { _: View, _: Track -> }) :
    ViewRenderer<Track, TrackViewModel>(Track::class.java) {

    @SuppressLint("CheckResult")
    override fun bindView(model: Track, holder: TrackViewModel) {

        holder.apply {
            val context = itemView.context
            uiName.text = model.name
            uiArtist.text = model.artist
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

    override fun createViewHolder(parent: ViewGroup): TrackViewModel {
        return TrackViewModel(inflate(R.layout.layout_item_album, parent))
    }

}