package com.mlcorrea.lostparadisefm.ui.feature.album.albums


import android.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.framework.extension.observe
import com.mlcorrea.lostparadisefm.framework.extension.viewModelInit
import com.mlcorrea.lostparadisefm.ui.base.BaseFragment
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter.AlbumViewRender
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivity
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivityVM
import com.mlcorrea.lostparadisefm.ui.renders.RendererRecyclerViewPagedAdapter
import com.mlcorrea.lostparadisefm.ui.renders.baserenders.LoadMoreViewRender


/**
 * A simple [Fragment] subclass.
 * Use the [AlbumListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AlbumListFragment : BaseFragment() {


    @BindView(R.id.ui_recycler_view)
    lateinit var uiRecyclerView: RecyclerView

    private lateinit var viewModel: AlbumListVM
    private lateinit var renderRecyclerView: RendererRecyclerViewPagedAdapter
    private var viewModelParent: MainActivityVM? = null

    override val fragmentLayout: Int get() = R.layout.fragment_album_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()

        viewModel = viewModelInit(viewModelFactoryB) {
            observe(listLiveData, ::handlerAdapter)
            observe(networkState, ::handlerNetwork)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activityParent = activity
        if (activityParent is MainActivity) {
            viewModelParent = activityParent.getViewModelParent()
        }
        viewModelParent?.queryUser?.observe(this, Observer { handleQuery(it) })
    }

    override fun onDestroy() {
        viewModelParent?.queryUser?.removeObservers(this)
        super.onDestroy()
    }

    private fun handleQuery(query: String?) {
        if (query == null) return
        viewModel.setQuery(query)
    }

    private fun setViews() {
        val albumsDiffCallback = object : DiffUtil.ItemCallback<ViewModelData>() {
            override fun areItemsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
                return if (oldItem is Album && newItem is Album) {
                    oldItem.artist == newItem.artist && oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }

            override fun areContentsTheSame(oldItem: ViewModelData, newItem: ViewModelData): Boolean {
                return if (oldItem is Album && newItem is Album) {
                    oldItem.artist == newItem.artist && oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }
        }
        //Set recycler view
        renderRecyclerView =
            RendererRecyclerViewPagedAdapter(
                diffCallback = albumsDiffCallback,
                loadMoreViewRender = LoadMoreViewRender(object :
                    LoadMoreViewRender.LoadMoreViewListener {
                    override fun onRetryClick() {
                        viewModel.retry()
                    }
                })
            )

        renderRecyclerView.registerRenderer(
            AlbumViewRender { view, album -> }
        )

        val gridLayout = GridLayoutManager(context, 1)
        uiRecyclerView.layoutManager = gridLayout
        uiRecyclerView.itemAnimator = DefaultItemAnimator()
        uiRecyclerView.setHasFixedSize(true)
        uiRecyclerView.adapter = renderRecyclerView
    }

    private fun handlerNetwork(networkRequestState: NetworkRequestState?) {
        renderRecyclerView.setNetworkState(networkRequestState)
        if (networkRequestState?.exception != null) {
            //showSnackBar(getErrorMessage(networkRequestState.exception as Exception))
        }
    }

    private fun handlerAdapter(pagedList: PagedList<ViewModelData>?) {
        if (pagedList == null) return
        renderRecyclerView.submitList(pagedList)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AlbumListFragment.
         */
        @JvmStatic
        fun newInstance() = AlbumListFragment()
    }
}
