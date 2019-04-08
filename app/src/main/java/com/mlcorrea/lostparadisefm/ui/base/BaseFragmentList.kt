package com.mlcorrea.lostparadisefm.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.renders.RendererRecyclerViewPagedAdapter
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.okButton

/**
 * Created by manuel on 07/04/19
 */
abstract class BaseFragmentList<T : ViewModelData, V : BaseDataSource>(
    private val layout: Int
) : BaseFragment() {

    @BindView(R.id.ui_recycler_view)
    lateinit var uiRecyclerView: RecyclerView

    abstract fun onCreateInitViewModel(savedInstanceState: Bundle?)
    abstract fun initViews()
    abstract val getViewModel: BaseViewModelPage<V, T>

    lateinit var renderRecyclerView: RendererRecyclerViewPagedAdapter

    override val fragmentLayout: Int
        get() = layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        onCreateInitViewModel(savedInstanceState)
        getViewModel.listLiveData.observe(this, Observer { handlerAdapter(it) })
        getViewModel.networkState.observe(this, Observer { handlerNetwork(it) })
    }

    private fun handlerNetwork(networkRequestState: NetworkRequestState?) {
        renderRecyclerView.setNetworkState(networkRequestState)
        networkRequestState?.exception?.let {
            displayErrorAlert(it as Exception)
        }
    }

    private fun displayErrorAlert(error: Exception) {
        activity?.apply {
            alert(Appcompat, getErrorMessage(error)) {
                okButton { }
            }.show()
        }
    }

    private fun handlerAdapter(pagedList: PagedList<ViewModelData>?) {
        if (pagedList == null) return
        renderRecyclerView.submitList(pagedList)
    }
}