package com.mlcorrea.lostparadisefm.ui.renders.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel.correa on 19/03/2018.
 */
class DefaultDiffUtilsCallback<VM : ViewModelData> : DiffUtil.ItemCallback<VM>() {
    override fun areItemsTheSame(oldItem: VM, newItem: VM): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VM, newItem: VM): Boolean {
        return oldItem == newItem
    }
}