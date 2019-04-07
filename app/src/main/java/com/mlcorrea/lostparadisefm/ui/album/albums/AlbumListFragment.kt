package com.mlcorrea.lostparadisefm.ui.album.albums


import android.app.Fragment
import android.os.Bundle
import android.view.View
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.ui.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [AlbumListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AlbumListFragment : BaseFragment() {


    override val fragmentLayout: Int get() = R.layout.fragment_album_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AlbumListFragment.
         */
        @JvmStatic
        fun newInstance() =
            AlbumListFragment()
    }
}
