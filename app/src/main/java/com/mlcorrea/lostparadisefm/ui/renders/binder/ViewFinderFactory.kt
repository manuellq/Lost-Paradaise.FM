package com.mlcorrea.lostparadisefm.ui.renders.binder

import android.view.View

/**
 * Created by manuel.correa on 29/05/2018
 */
interface ViewFinderFactory {
    companion object Factory {
        fun createViewFinder(itemView: View): ViewFinder {
            return ViewFinderImpl(itemView)
        }
    }
}

fun ViewFinderFactory.Factory.fromView(itemView: View) = this.createViewFinder(itemView)