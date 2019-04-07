package com.mlcorrea.lostparadisefm.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by manuel on 06/04/19
 */
abstract class BaseFragment : Fragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var activityContext: Context
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactoryB: ViewModelProvider.Factory

    /*------ abstract methods ------*/

    protected abstract val fragmentLayout: Int

    /*------ end abstract methods ------*/

    private var unbinder: Unbinder? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(fragmentLayout, container, false)
        injectViews(view)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unbinder?.unbind()
    }

    /*------------------FOR DEPENDENCY INJECTION-----------------*/

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return childFragmentInjector
    }

    /*-------------PRIVATE METHOD----------*/

    private fun injectViews(view: View) {
        this.unbinder = ButterKnife.bind(this, view)
    }
}
