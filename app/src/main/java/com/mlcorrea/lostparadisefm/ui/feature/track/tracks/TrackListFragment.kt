package com.mlcorrea.lostparadisefm.ui.feature.track.tracks


import android.app.Fragment
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Track
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.base.BaseFragmentList
import com.mlcorrea.lostparadisefm.ui.base.BaseViewModelPage
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivity
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivityVM
import com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo.TrackInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.adapter.TrackDataSource
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.adapter.TrackDiffCallback
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.adapter.TrackViewRender
import com.mlcorrea.lostparadisefm.ui.renders.RendererRecyclerViewPagedAdapter
import com.mlcorrea.lostparadisefm.ui.renders.baserenders.LoadMoreViewRender
import com.mlcorrea.lostparadisefm.ui.utils.getUrlImage
import org.koin.androidx.scope.currentScope

/**
 * A simple [Fragment] subclass.
 *
 */
class TrackListFragment : BaseFragmentList<Track, TrackDataSource>(R.layout.fragment_track_list) {


    private val viewModel: TrackListVM by currentScope.inject()
    private var viewModelParent: MainActivityVM? = null

    override val getViewModel: BaseViewModelPage<TrackDataSource, Track> get() = viewModel

    override fun onCreateInitViewModel(savedInstanceState: Bundle?) {

    }

    override fun initViews() {
        //Set recycler view
        renderRecyclerView =
            RendererRecyclerViewPagedAdapter(
                diffCallback = TrackDiffCallback(),
                loadMoreViewRender = LoadMoreViewRender(object :
                    LoadMoreViewRender.LoadMoreViewListener {
                    override fun onRetryClick() {
                        viewModel.retry()
                    }
                })
            )

        renderRecyclerView.registerRenderer(
            TrackViewRender { view, track ->
                activity?.let {
                    val intent = TrackInfoActivity.newIntent(
                        it,
                        track.artist ?: " ",
                        track.name,
                        track.images.getUrlImage(TypeImage.EXTRA_LARGE)
                    )
                    startActivity(intent)
                }
            }
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
         * @return A new instance of fragment TrackListFragment.
         */
        @JvmStatic
        fun newInstance() = TrackListFragment()
    }


}
