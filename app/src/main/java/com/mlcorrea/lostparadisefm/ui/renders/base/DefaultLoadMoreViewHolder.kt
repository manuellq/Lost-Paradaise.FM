package com.mlcorrea.lostparadisefm.ui.renders.base

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.ViewHolderWF

/**
 * Created by manuel.correa on 20/03/2018.
 */
class DefaultLoadMoreViewHolder(itemView: View) : ViewHolderWF(itemView) {

    val uiRetryBtn: Button = itemView.findViewById<View>(R.id.retryLoadingButton) as Button
    val uiProgressBar: ProgressBar =
        itemView.findViewById<View>(R.id.loadingProgressBar) as ProgressBar
    val uiErrorMessage: TextView = itemView.findViewById<View>(R.id.errorMessageTextView) as TextView
}