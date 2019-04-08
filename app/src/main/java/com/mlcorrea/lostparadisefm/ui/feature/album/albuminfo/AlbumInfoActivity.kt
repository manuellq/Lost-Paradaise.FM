package com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo

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
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.model.response.ResponseRx
import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.framework.extension.observe
import com.mlcorrea.lostparadisefm.framework.extension.viewModelInit
import com.mlcorrea.lostparadisefm.ui.base.BaseActivity
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.adapter.AlbumInfoComparator
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.adapter.AlbumInfoViewRender
import com.mlcorrea.lostparadisefm.ui.renders.RendererRecyclerViewSortedAdapter
import com.mlcorrea.lostparadisefm.ui.renders.diffutils.SortedListComparatorWrapper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

class AlbumInfoActivity : BaseActivity() {

    @BindView(R.id.ui_image_album)
    lateinit var uiImage: ImageView
    @BindView(R.id.ui_collapsing_toolbar)
    lateinit var uiCollapsingToolbar: CollapsingToolbarLayout
    @BindView(R.id.ui_recycler_view)
    lateinit var uiRecyclerView: RecyclerView


    private lateinit var viewModel: AlbumInfoVM
    private lateinit var renderRecyclerView: RendererRecyclerViewSortedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_info)
        ButterKnife.bind(this)
        setupToolbar()
        initViews()
        viewModel = viewModelInit(viewModelFactoryBis) {
            observe(networkState, ::handleNetwork)
            observe(albumData, ::handleResponse)
        }

        intent.extras?.let {
            val artist = it.getString(INTENT_EXTRA_ARTIST)
            val album = it.getString(INTENT_EXTRA_ALBUM)
            val image = if (it.containsKey(INTENT_EXTRA_IMAGE)) it.getString(INTENT_EXTRA_IMAGE) else null
            if (artist == null || album == null) {
                finish()
            } else {
                updateUI(artist, album, image)
                viewModel.initVM(artist, album, image)
            }
        }
    }

    private fun handleNetwork(networkRequestState: NetworkRequestState?) {
        renderRecyclerView.setNetworkState(networkRequestState)
    }

    private fun initViews() {

        renderRecyclerView =
            RendererRecyclerViewSortedAdapter()
        val sortedListComparatorWrapper: SortedListComparatorWrapper<ViewModelData>
        sortedListComparatorWrapper = AlbumInfoComparator(renderRecyclerView)
        renderRecyclerView.setComparatorWrapper(sortedListComparatorWrapper)
        renderRecyclerView.registerRenderer(AlbumInfoViewRender())

        val gridLayout = GridLayoutManager(this, 1)
        uiRecyclerView.layoutManager = gridLayout
        uiRecyclerView.itemAnimator = DefaultItemAnimator()
        uiRecyclerView.setHasFixedSize(true)
        uiRecyclerView.adapter = renderRecyclerView
    }

    private fun handleResponse(responseRx: ResponseRx<Album>?) {
        when (responseRx) {
            is ResponseRx.Loading -> {
                //
            }
            is ResponseRx.Error -> displayErrorAlert(responseRx.exception)
            is ResponseRx.Success -> {
                responseRx.data?.tracks.let {
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

    private fun updateUI(artist: String?, album: String?, image: String?) {
        setTitleToolBar(album)
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
        private const val INTENT_EXTRA_ALBUM = "INTENT_EXTRA_ALBUM"
        private const val INTENT_EXTRA_IMAGE = "INTENT_EXTRA_IMAGE"

        @JvmStatic
        fun newIntent(context: Context, artist: String, album: String, image: String?): Intent {
            return Intent(context, AlbumInfoActivity::class.java).apply {
                putExtra(INTENT_EXTRA_ARTIST, artist)
                putExtra(INTENT_EXTRA_ALBUM, album)
                image?.let {
                    putExtra(INTENT_EXTRA_IMAGE, image)
                }
            }
        }
    }
}
