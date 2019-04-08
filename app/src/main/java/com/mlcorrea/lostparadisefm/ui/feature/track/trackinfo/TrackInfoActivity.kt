package com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mlcorrea.domain.model.Track
import com.mlcorrea.domain.model.response.ResponseRx
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.framework.extension.observe
import com.mlcorrea.lostparadisefm.framework.extension.viewModelInit
import com.mlcorrea.lostparadisefm.ui.base.BaseActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

class TrackInfoActivity : BaseActivity() {


    @BindView(R.id.ui_image_track)
    lateinit var uiImage: ImageView
    @BindView(R.id.ui_collapsing_toolbar)
    lateinit var uiCollapsingToolbar: CollapsingToolbarLayout

    @BindView(R.id.group_text)
    lateinit var uiGroup: Group
    @BindView(R.id.ui_progress_bar)
    lateinit var uiProgressBar: ProgressBar

    @BindView(R.id.text_title_input)
    lateinit var uiTextTitle: TextView
    @BindView(R.id.text_artist_input)
    lateinit var uiTextArtist: TextView
    @BindView(R.id.text_title)
    lateinit var uiTitle: TextView

    @BindView(R.id.text_summary)
    lateinit var uiSummary: TextView
    @BindView(R.id.text_content)
    lateinit var uiContent: TextView


    @BindView(R.id.ui_card)
    lateinit var uiCardView: CardView


    private lateinit var viewModel: TrackInfoVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_info)
        ButterKnife.bind(this)
        setupToolbar()
        viewModel = viewModelInit(viewModelFactoryBis) {
            observe(trackData, ::handleResponse)
        }
        intent.extras?.let {
            val artist = it.getString(INTENT_EXTRA_ARTIST)
            val track = it.getString(INTENT_EXTRA_TRACK)
            val image = if (it.containsKey(INTENT_EXTRA_IMAGE)) it.getString(INTENT_EXTRA_IMAGE) else null
            if (artist == null || track == null) {
                finish()
            } else {
                updateUI(track, image)
                viewModel.initVM(artist, track)
            }
        }
    }

    private fun handleResponse(responseRx: ResponseRx<Track>?) {
        when (responseRx) {
            is ResponseRx.Loading -> progressBarStatus(true)
            is ResponseRx.Error -> {
                uiCardView.visibility = View.GONE
                displayErrorAlert(responseRx.exception)
            }
            is ResponseRx.Success -> {
                progressBarStatus(false)
                uiTextTitle.text = responseRx.data?.album?.title
                val isTitle = responseRx.data?.album?.title.isNullOrEmpty()
                uiTextTitle.visibility = if (isTitle) View.GONE else View.VISIBLE
                uiTitle.visibility = if (isTitle) View.GONE else View.VISIBLE
                uiTextArtist.text = responseRx.data?.artist
                uiSummary.text = responseRx.data?.wiki?.summary
                uiSummary.visibility = if (responseRx.data?.wiki?.summary.isNullOrEmpty()) View.GONE else View.VISIBLE
                uiContent.text = responseRx.data?.wiki?.content
                uiContent.visibility = if (responseRx.data?.wiki?.content.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
        }
    }

    private fun progressBarStatus(visible: Boolean) {
        uiCardView.visibility = View.VISIBLE
        uiProgressBar.visibility = if (visible) View.VISIBLE else View.GONE
        uiGroup.visibility = if (visible) View.GONE else View.VISIBLE
    }

    private fun displayErrorAlert(error: Exception) {
        alert(Appcompat, getUserMessageError(error)) {
            okButton { viewModel.retry() }
            cancelButton { }
        }.show()
            .setCancelable(false)
    }

    private fun updateUI(track: String?, image: String?) {
        setTitleToolBar(track)
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

    private fun setupToolbar() {
        setToolbar()
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
        private const val INTENT_EXTRA_TRACK = "INTENT_EXTRA_TRACK"
        private const val INTENT_EXTRA_IMAGE = "INTENT_EXTRA_IMAGE"

        @JvmStatic
        fun newIntent(context: Context, artist: String, track: String, image: String?): Intent {
            return Intent(context, TrackInfoActivity::class.java).apply {
                putExtra(INTENT_EXTRA_ARTIST, artist)
                putExtra(INTENT_EXTRA_TRACK, track)
                image?.let {
                    putExtra(INTENT_EXTRA_IMAGE, image)
                }
            }
        }
    }

}
