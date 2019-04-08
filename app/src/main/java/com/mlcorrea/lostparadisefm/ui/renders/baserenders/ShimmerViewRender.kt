package com.mlcorrea.lostparadisefm.ui.renders.baserenders

import android.view.ViewGroup
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.base.DefaultShimmerViewHolder
import com.mlcorrea.lostparadisefm.ui.renders.base.DefaultShimmerViewRender
import com.mlcorrea.lostparadisefm.ui.renders.model.ShimmerModal

/**
 * Created by manuel.correa on 21/03/2018.
 */
class ShimmerViewRender : DefaultShimmerViewRender() {

    override fun bindView(model: ShimmerModal, holder: DefaultShimmerViewHolder) {
    }

    override fun createViewHolder(parent: ViewGroup): DefaultShimmerViewHolder {
        return DefaultShimmerViewHolder(
            inflate(R.layout.layout_shimmer_list_default, parent)
        )
    }
}