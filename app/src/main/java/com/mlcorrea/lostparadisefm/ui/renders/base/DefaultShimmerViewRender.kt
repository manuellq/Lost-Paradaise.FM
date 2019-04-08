package com.mlcorrea.lostparadisefm.ui.renders.base

import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer
import com.mlcorrea.lostparadisefm.ui.renders.model.ShimmerModal

/**
 * Created by manuel.correa on 21/03/2018.
 */
abstract class DefaultShimmerViewRender() :
    ViewRenderer<ShimmerModal, DefaultShimmerViewHolder>(ShimmerModal::class.java)