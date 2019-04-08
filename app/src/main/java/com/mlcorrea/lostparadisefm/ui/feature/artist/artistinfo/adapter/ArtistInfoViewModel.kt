package com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewHolderWF

/**
 * Created by manuel.correa on 08/04/19
 */
class ArtistInfoViewModel(itemView: View) : ViewHolderWF(itemView) {
    val uiImage: ImageView = itemView.findViewById<View>(R.id.io_image) as ImageView
    val uiText: TextView = itemView.findViewById<View>(R.id.ui_text) as TextView
}