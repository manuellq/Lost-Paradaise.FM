package com.mlcorrea.lostparadisefm.ui.renders

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.ui.renders.base.DefaultEmptyStateViewHolder
import com.mlcorrea.lostparadisefm.ui.renders.base.DefaultEmptyStateViewRender
import com.mlcorrea.lostparadisefm.ui.renders.base.DefaultLoadMoreViewRender
import com.mlcorrea.lostparadisefm.ui.renders.baserenders.EmptyListViewRender
import com.mlcorrea.lostparadisefm.ui.renders.baserenders.LoadMoreViewRender
import com.mlcorrea.lostparadisefm.ui.renders.diffutils.DefaultDiffUtilsCallback
import com.mlcorrea.lostparadisefm.ui.renders.model.EmptyListModel
import java.lang.reflect.Type

/**
 * Created by manuel on 07/04/19
 */
class RendererRecyclerViewPagedAdapter(
    private val loadMoreViewRender: DefaultLoadMoreViewRender<*> = LoadMoreViewRender(null),
    private val emptyViewRender: DefaultEmptyStateViewRender = EmptyListViewRender(),
    diffCallback: DiffUtil.ItemCallback<ViewModelData> = DefaultDiffUtilsCallback()
) :
    PagedListAdapter<ViewModelData, ViewHolderWF>(diffCallback) {

    private val mRenderers = ArrayList<ViewRenderer<*, *>>()
    private val mTypes = ArrayList<Type>()
    private var networkState: NetworkRequestState? = null

    init {
        registerRenderer(emptyViewRender)
        registerRenderer(loadMoreViewRender)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderWF {
        val renderer = mRenderers[viewType]
        if (renderer != null) {
            return renderer.createViewHolder(parent)
        }
        throw RuntimeException("Not supported Item View Type: $viewType")
    }

    override fun onBindViewHolder(holder: ViewHolderWF, position: Int) {
        if (getItemViewType(position) == getTypeIndex(loadMoreViewRender.getType())) {
            (loadMoreViewRender as DefaultLoadMoreViewRender<ViewHolderWF>)
                .performBindView(networkState ?: NetworkRequestState.LOADING, holder)
        } else if (getItemViewType(position) == getTypeIndex(emptyViewRender.getType())) {
            emptyViewRender.performBindView(EmptyListModel(), holder as DefaultEmptyStateViewHolder)
        } else {
            val item = getItemFromList<ViewModelData>(position)
            val renderer = getRenderer(item) as ViewRenderer<ViewModelData, ViewHolderWF>
            if (renderer != null) {
                renderer.performBindView(item, holder)
            } else {
                throw RuntimeException("Not supported View Holder: $holder")
            }
        }
    }

    /**
     * The ItemViewType is the term of the RecyclerView, We never use this term.
     */
    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            return when (networkState) {
                NetworkRequestState.EMPTY -> getTypeIndex(emptyViewRender.getType())
                else -> getTypeIndex(loadMoreViewRender.getType())
            }
        } else {
            getTypeIndex(position)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    /*---------------*/

    fun registerRenderer(renderer: ViewRenderer<*, *>) {
        val type = renderer.getType()
        if (!mTypes.contains(type)) {
            mTypes.add(type)
            mRenderers.add(renderer)
        } else {
            throw RuntimeException("ViewRenderer already registered for this type: $type")
        }
    }

    fun setNetworkState(newNetworkState: NetworkRequestState?) {
        this.networkState = newNetworkState
        notifyDataSetChanged()
    }

    private fun <T : ViewModelData> getItemFromList(position: Int): T {
        return getItem(position) as T
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkRequestState.LOADED
    }

    private fun getTypeIndex(position: Int): Int {
        val model = getItemFromList<ViewModelData>(position)
        return getTypeIndex(model)
    }

    private fun getTypeIndex(model: ViewModelData): Int {
        return getTypeIndex(model.javaClass)
    }

    private fun getTypeIndex(type: Type): Int {
        val typeIndex = mTypes.indexOf(type)

        if (typeIndex == -1) {
            throw RuntimeException("ViewRenderer not registered for this type: $type")
        }
        return typeIndex
    }

    private fun getRenderer(position: Int): ViewRenderer<*, *> {
        val typeIndex = getTypeIndex(position)
        return mRenderers[typeIndex]
    }

    private fun getRenderer(model: ViewModelData): ViewRenderer<*, *> {
        val typeIndex = getTypeIndex(model)
        return mRenderers[typeIndex]
    }

    private fun getRenderer(type: Type): ViewRenderer<*, *> {
        val typeIndex = getTypeIndex(type)
        return mRenderers[typeIndex]
    }
}