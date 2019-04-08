package com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.adapter

import android.view.View
import android.widget.TextView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewHolderWF

/**
 * Created by manuel.correa on 08/04/19
 */
class AlbumInfoViewModel(itemView: View) : ViewHolderWF(itemView) {
    val uiText: TextView = itemView.findViewById<View>(R.id.ui_text) as TextView
}