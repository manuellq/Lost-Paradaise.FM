package com.mlcorrea.lostparadisefm.ui.feature.home

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.base.BaseActivity
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.AlbumListFragment
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.ArtistsFragment
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.TrackListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.scope.currentScope
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {


    @BindView(R.id.navigation)
    lateinit var uiBottomNavigation: BottomNavigationView
    @BindView(R.id.toolbar)
    lateinit var uiToolbar: Toolbar

    private val disposables = CompositeDisposable()
    private val viewModel: MainActivityVM by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initViews()
        addFragment(R.id.ui_main_container, AlbumListFragment.newInstance())
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun getViewModelParent(): MainActivityVM {
        return viewModel
    }

    private fun initViews() {
        uiBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        uiToolbar.inflateMenu(R.menu.menu_search)

        val searchItem = uiToolbar.menu?.findItem(R.id.menu_action_search)
        val search = searchItem?.actionView as? SearchView
        search?.let {
            disposables.add(
                RxSearchView.queryTextChanges(it)
                    .skip(1)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ text: CharSequence? ->
                        text?.let { textToSearch ->
                            viewModel.setQuery(textToSearch.toString())
                        }
                    }, { })
            )
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val visibleFragment = supportFragmentManager.findFragmentById(R.id.ui_main_container)
            when (item.itemId) {
                R.id.navigation_album -> {
                    if (visibleFragment !is AlbumListFragment){
                        addFragment(R.id.ui_main_container, AlbumListFragment.newInstance())
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_artist -> {
                    if (visibleFragment !is ArtistsFragment){
                        addFragment(R.id.ui_main_container, ArtistsFragment.newInstance())
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_track -> {
                    if (visibleFragment !is TrackListFragment){
                        addFragment(R.id.ui_main_container, TrackListFragment.newInstance())
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

//    private fun <T>isFragmentVisible(fragment: Class<T>): Boolean {
//        val visibleFragment = supportFragmentManager.findFragmentById(R.id.ui_main_container)
//
//        if (visibleFragment is fragment){
//
//        }
//    }
}
