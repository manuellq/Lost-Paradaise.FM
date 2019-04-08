package com.mlcorrea.lostparadisefm.ui.renders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.renders.binder.ViewFinder
import com.mlcorrea.lostparadisefm.ui.renders.binder.ViewFinderFactory

/**
 * Created by manuel.correa on 22/01/2018.
 */
open class ViewHolderWF(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mType: Class<out ViewModelData>? = null
    private var viewFinder: ViewFinder? = null

    fun getViewFinder(): ViewFinder {
        val newViewFinder = viewFinder
        return if (newViewFinder == null) {
            ViewFinderFactory.createViewFinder(itemView)
        } else {
            return newViewFinder
        }
    }

    fun setType(type: Class<out ViewModelData>) {
        mType = type
    }

    /**
     * @return - type of bound ViewModel
     */
    fun getType(): Class<out ViewModelData> {
        return mType ?: throw IllegalArgumentException("View holder must have a type class")
    }
}