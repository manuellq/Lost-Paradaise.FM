package com.mlcorrea.lostparadisefm.ui.renders.base

import android.view.View
import android.widget.TextView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewHolderWF

/**
 * Created by manuel.correa on 27/03/2018.
 */
class DefaultEmptyStateViewHolder(itemView: View) : ViewHolderWF(itemView) {

    val uiMessage: TextView = itemView.findViewById<View>(R.id.ui_text_description) as TextView
}