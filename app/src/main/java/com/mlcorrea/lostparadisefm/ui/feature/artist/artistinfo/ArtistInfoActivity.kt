package com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.model.response.ResponseRx
import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.framework.extension.observe
import com.mlcorrea.lostparadisefm.framework.extension.viewModelInit
import com.mlcorrea.lostparadisefm.ui.base.BaseActivity
import com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.adapter.ArtistInfoComparator
import com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.adapter.ArtistInfoViewRender
import com.mlcorrea.lostparadisefm.ui.renders.RendererRecyclerViewSortedAdapter
import com.mlcorrea.lostparadisefm.ui.renders.diffutils.SortedListComparatorWrapper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

class ArtistInfoActivity : BaseActivity() {

    @BindView(R.id.ui_image_artist)
    lateinit var uiImage: ImageView
    @BindView(R.id.ui_collapsing_toolbar)
    lateinit var uiCollapsingToolbar: CollapsingToolbarLayout
    @BindView(R.id.ui_recycler_view)
    lateinit var uiRecyclerView: RecyclerView


    private lateinit var viewModel: ArtistInfoVM
    private lateinit var renderRecyclerView: RendererRecyclerViewSortedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_info)

        ButterKnife.bind(this)
        setupToolbar()
        initViews()
        viewModel = viewModelInit(viewModelFactoryBis) {
            observe(networkState, ::handleNetwork)
            observe(artistData, ::handleResponse)
        }

        intent.extras?.let {
            val artist = it.getString(INTENT_EXTRA_ARTIST)
            val image =
                if (it.containsKey(INTENT_EXTRA_IMAGE)) it.getString(INTENT_EXTRA_IMAGE) else null
            if (artist == null) {
                finish()
            } else {
                updateUI(artist, image)
                viewModel.initVM(artist)
            }
        }
    }

    private fun handleNetwork(networkRequestState: NetworkRequestState?) {
        renderRecyclerView.setNetworkState(networkRequestState)
    }

    private fun initViews() {

        renderRecyclerView = RendererRecyclerViewSortedAdapter()
        val sortedListComparatorWrapper: SortedListComparatorWrapper<ViewModelData>
        sortedListComparatorWrapper = ArtistInfoComparator(renderRecyclerView)
        renderRecyclerView.setComparatorWrapper(sortedListComparatorWrapper)
        renderRecyclerView.registerRenderer(ArtistInfoViewRender())

        val gridLayout = GridLayoutManager(this, 3)
        uiRecyclerView.layoutManager = gridLayout
        uiRecyclerView.itemAnimator = DefaultItemAnimator()
        uiRecyclerView.setHasFixedSize(true)
        uiRecyclerView.adapter = renderRecyclerView
    }

    private fun handleResponse(responseRx: ResponseRx<Artist>?) {
        when (responseRx) {
            is ResponseRx.Loading -> {
                //
            }
            is ResponseRx.Error -> displayErrorAlert(responseRx.exception)
            is ResponseRx.Success -> {
                responseRx.data?.similar?.artists?.let {
                    renderRecyclerView.removeAll(it as List<ViewModelData>)
                }
            }
        }
    }

    private fun displayErrorAlert(error: Exception) {
        alert(Appcompat, getUserMessageError(error)) {
            okButton { viewModel.retry() }
            cancelButton { }
        }.show()
            .setCancelable(false)
    }

    private fun setupToolbar() {
        setToolbar()
    }

    private fun updateUI(artist: String?, image: String?) {
        setTitleToolBar(artist)
        if (image != null && image.isNotBlank() && image.isNotEmpty()) {
            Picasso.with(this)
                .load(image)
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorAccent)
                .into(uiImage,
                    object : Callback {
                        override fun onSuccess() {
                            val bitmap = (uiImage.drawable as BitmapDrawable).bitmap
                            Palette.from(bitmap).generate { palette ->
                                palette?.let {
                                    applyPalette(it)
                                }
                            }
                        }

                        override fun onError() {
                            //
                        }
                    })
        }
    }

    private fun applyPalette(palette: Palette) {
        val primaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val primary = ContextCompat.getColor(this, R.color.colorPrimary)
        uiCollapsingToolbar.setContentScrimColor(palette.getMutedColor(primary))
        uiCollapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark))
        uiCollapsingToolbar.setExpandedTitleColor(palette.getMutedColor(primary))
    }

    companion object {
        private const val INTENT_EXTRA_ARTIST = "INTENT_EXTRA_ARTIST"
        private const val INTENT_EXTRA_IMAGE = "INTENT_EXTRA_IMAGE"

        @JvmStatic
        fun newIntent(context: Context, artist: String, image: String?): Intent {
            return Intent(context, ArtistInfoActivity::class.java).apply {
                putExtra(INTENT_EXTRA_ARTIST, artist)
                image?.let {
                    putExtra(INTENT_EXTRA_IMAGE, image)
                }
            }
        }
    }

}
