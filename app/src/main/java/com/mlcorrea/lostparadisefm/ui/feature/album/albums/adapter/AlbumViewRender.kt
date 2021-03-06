package com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Album
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer
import com.mlcorrea.lostparadisefm.ui.utils.getUrlImage
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by manuel on 07/04/19
 */
class AlbumViewRender(private val clickListener: (View, Album) -> Unit = { _: View, _: Album -> }) :
    ViewRenderer<Album, AlbumViewModel>(Album::class.java) {

    @SuppressLint("CheckResult")
    override fun bindView(model: Album, holder: AlbumViewModel) {

        holder.apply {
            val context = itemView.context
            uiArtist.text = model.artist
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

    override fun createViewHolder(parent: ViewGroup): AlbumViewModel {
        return AlbumViewModel(
            inflate(R.layout.layout_item_album, parent)
        )
    }


}