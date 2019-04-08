package com.mlcorrea.lostparadisefm.ui.renders.base

import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.ui.renders.ViewHolderWF
import com.mlcorrea.lostparadisefm.ui.renders.ViewRenderer

/**
 * Created by manuel.correa on 21/03/2018.
 */
abstract class DefaultLoadMoreViewRender<VH : ViewHolderWF> :
    ViewRenderer<NetworkRequestState, VH>(NetworkRequestState::class.java)