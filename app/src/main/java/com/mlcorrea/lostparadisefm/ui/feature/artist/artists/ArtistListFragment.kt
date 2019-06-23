package com.mlcorrea.lostparadisefm.ui.feature.artist.artists


import android.app.Fragment
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.base.BaseFragmentList
import com.mlcorrea.lostparadisefm.ui.base.BaseViewModelPage
import com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.ArtistInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter.ArtistDataSource
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter.ArtistDiffCallback
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter.ArtistViewRender
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivity
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivityVM
import com.mlcorrea.lostparadisefm.ui.renders.RendererRecyclerViewPagedAdapter
import com.mlcorrea.lostparadisefm.ui.renders.baserenders.LoadMoreViewRender
import com.mlcorrea.lostparadisefm.ui.utils.getUrlImage
import org.koin.androidx.scope.currentScope


/**
 * A simple [Fragment] subclass.
 *
 */
class ArtistsFragment : BaseFragmentList<Artist, ArtistDataSource>(R.layout.fragment_artist_list) {

    private val viewModel: ArtistListVM by currentScope.inject()
    private var viewModelParent: MainActivityVM? = null


    override val getViewModel: BaseViewModelPage<ArtistDataSource, Artist>
        get() = viewModel

    override fun onCreateInitViewModel(savedInstanceState: Bundle?) {
    }

    override fun initViews() {
        //Set recycler view
        renderRecyclerView =
            RendererRecyclerViewPagedAdapter(
                diffCallback = ArtistDiffCallback(),
                loadMoreViewRender = LoadMoreViewRender(object :
                    LoadMoreViewRender.LoadMoreViewListener {
                    override fun onRetryClick() {
                        viewModel.retry()
                    }
                })
            )

        renderRecyclerView.registerRenderer(
            ArtistViewRender { view, artist ->
                activity?.let {
                    val intent = ArtistInfoActivity.newIntent(
                        it,
                        artist.name,
                        artist.images.getUrlImage(TypeImage.MEDIUM)
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
         * @return A new instance of fragment ArtistsFragment.
         */
        @JvmStatic
        fun newInstance() = ArtistsFragment()
    }

}
