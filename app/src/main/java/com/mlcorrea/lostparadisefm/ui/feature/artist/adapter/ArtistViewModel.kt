package com.mlcorrea.lostparadisefm.ui.feature.artist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewHolderWF

/**
 * Created by manuel on 07/04/19
 */
class ArtistViewModel(itemView: View) : ViewHolderWF(itemView) {
    val uiName: TextView = itemView.findViewById<View>(R.id.ui_name) as TextView
    val uiArtist: TextView = itemView.findViewById<View>(R.id.ui_artist) as TextView
    val uiImage: ImageView = itemView.findViewById<View>(R.id.ui_image_album) as ImageView
}