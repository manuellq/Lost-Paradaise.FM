package com.mlcorrea.lostparadisefm.ui.home

import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.album.albums.AlbumListFragment
import com.mlcorrea.lostparadisefm.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    @BindView(R.id.navigation)
    lateinit var uiBottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initViews()
        addFragment(R.id.ui_main_container, AlbumListFragment.newInstance())
    }

    private fun initViews() {
        uiBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_album -> {
                    addFragment(R.id.ui_main_container, AlbumListFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_artist -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_track -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
