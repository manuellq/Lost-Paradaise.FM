package com.mlcorrea.lostparadisefm.ui.feature.album.albums


import android.app.Fragment
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.mlcorrea.domain.model.Album
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.framework.extension.viewModelInit
import com.mlcorrea.lostparadisefm.ui.base.BaseFragmentList
import com.mlcorrea.lostparadisefm.ui.base.BaseViewModelPage
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter.AlbumDataSource
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter.AlbumDiffCallback
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
class AlbumListFragment : BaseFragmentList<Album, AlbumDataSource>(R.layout.fragment_album_list) {


    private lateinit var viewModel: AlbumListVM
    private var viewModelParent: MainActivityVM? = null

    override val getViewModel: BaseViewModelPage<AlbumDataSource, Album>
        get() = viewModel

    override fun onCreateInitViewModel(savedInstanceState: Bundle?) {
        viewModel = viewModelInit(viewModelFactoryB) {
        }
    }

    override fun initViews() {
        //Set recycler view
        renderRecyclerView =
            RendererRecyclerViewPagedAdapter(
                diffCallback = AlbumDiffCallback(),
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
